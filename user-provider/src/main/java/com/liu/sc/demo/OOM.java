package com.liu.sc.demo;

import org.apache.ibatis.ognl.enhance.EnhancedClassLoader;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * 内存溢出
 */
public class OOM {

}

/**
 * 栈溢出
 */
class StackOverFlowErrorDemo{
    public static void main(String[] args) {
        stackOverFlowError();
    }

    private static void stackOverFlowError() {
        stackOverFlowError();
    }
}

/**
 * 堆溢出
 */
class JavaHeapSpaceDemo{
    public static void main(String[] args) {
        byte[] bytes = new byte[30 * 1024 * 1024];
    }
}

/**
 * GC回收时间过长
 *
 * GC回收时间过长时会抛出OutOfMemoryError：gc overhead limit exceeded
 * 过长的定义是：超过98%的时间用来做GC并且回收了不到2%的堆内存，连续多次GC都只回收了不到2%的堆内存的情况下抛出。
 * 假如不抛出gc overhead limit错误会发生什么情况？
 * GC清理的内存会被再次填满，迫使GC再次执行，这样就造成恶性循环，CPU使用率一直是100%，而GC没有任何效果
 *
 * JVM参数配置演示
 * -Xms5m -Xmx5m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=2m
 */
class GCOverHeadLimitExceeded{

    public static void main(String[] args) {
        int i = 0;
        ArrayList<String> list = new ArrayList<>();

        try {
            while (true){
                list.add(String.valueOf(++i).intern());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/**
 * 本地内存溢出，不在GC范围内
 * 参数配置：-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 *
 * 导致原因：
 * 写NIO程序经常使用ByteBuffer来读取或者写入数据，这是一种基于通道（channel）和缓冲区（buffer）的I/O方式，
 * 它可以使用native函数库直接分配堆外内存，然后通过一个存储在java堆里面的DirectByteBuffer对象作为这块内存的引用操作
 * 这样能在一些场景中显著提高性能，因为避免了在java堆和native堆中来回复制数据
 * ByteBuffer.allocate(capability)：分配java堆内存，属于GC范畴，由于需要拷贝所以速度相对较慢
 * ByteBuffer.allocateDirect(capability)：分配os本地内存，不属于GC范畴，由于不需要内存拷贝所以速度相对较快
 *
 * 但如果不断分配本地内存，堆内存很少使用，那么JVM就不需要执行GC，DirectByteBuffer对象就不会被回收，
 * 这时候堆内存充足，但本地内存已经使用完了，再次尝试分配本地内存就会出现OutOfMemoryError，程序直接崩溃
 */
class DirectBufferMemory{
    public static void main(String[] args) {
        System.out.println("配置的maxDirectMemory：" + sun.misc.VM.maxDirectMemory() / (double)1024 /1024 + "MB");
        ByteBuffer.allocateDirect(6 * 1024 * 1024);
    }
}

/**
 * 高并发请求服务器时，经常出现如下异常：java.lang.OutOfMemoryError：unable to create new native thread
 * 准确的将native thread 异常与对应的平台有关
 *
 * 导致原因：
 * 1. 一个应用创建了太多线程，一个应用进程创建多个线程，超过了系统承载极限
 * 2. 服务器并不允许应用创建这么多线程，linux系统默认允许单个进程可以创建的线程数是1024个(非root用户)，root用户无上限
 *    一个进程的线程数如果超过1024个，那么就会报java.lang.OutOfMemoryError：unable to create new native thread
 *
 * 解决办法：
 * 1. 想办法降低应用程序创建的线程数量，分析应用是否真的需要创建这么多线程，如果不是，修改代码将线程数降到最低
 * 2. 对于有的应用，确实需要创建很多线程，远超过linux系统默认的1024个的限制，可以通过修改linux服务配置，扩大线程配置
 * vim /etc/security/limits.d/90-nproc.conf
 */
class UnableToCreateNewNativeThread{
    public static void main(String[] args){
        for (int i=1;;i++){
            System.out.println(i);
            new Thread(() -> {
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}

/**
 * 元空间溢出:java8及以后的版本使用元空间替代永久代
 *
 * JVM参数
 * -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m
 *
 * MetaSpace是方法区在HotSpot中的实现，它与持久代最大的区别在于：MetaSpace并不在虚拟机内存中而是使用本地内存，
 * 也就是说在java8中，class metadata(the virtual machines internal presentation of java class)，被存储在叫做
 * MetaSpace的native memory
 *
 * 永久代（java8后被元空间MetaSpace取代）存放一下信息
 * 虚拟机加载的类信息、常量池、静态变量、即时编译后的代码
 *
 */
class MetaSpace{
    public static void main(String[] args) {
        int i = 0;
        try {
            while (true) {
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOM.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o,args);
                    }
                });
                enhancer.create();
            }
        } catch (Exception e) {
            System.out.println(i + "发生了metaSpace");
            e.printStackTrace();
        }
    }

    static class OOMTest{

    }
}
