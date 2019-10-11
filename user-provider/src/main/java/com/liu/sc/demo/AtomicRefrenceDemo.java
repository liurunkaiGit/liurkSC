package com.liu.sc.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.jws.soap.SOAPBinding;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Description: 原子引用demo
 * @Author:W_LIURUNKAI
 * @Date:2019/8/25 15:54
 */
@Slf4j
public class AtomicRefrenceDemo {

    public static void main(String[] args) {
        User zs = new User("zs", 22);
        User ls = new User("ls", 11);
        AtomicReference<User> userAtomicReference = new AtomicReference<>();
        userAtomicReference.set(zs);
        boolean b = userAtomicReference.compareAndSet(zs, ls);
        log.info("b is {},user is {}", b, userAtomicReference.get());
        boolean b1 = userAtomicReference.compareAndSet(zs, ls);
        log.info("b1 is {},user is {}", b1, userAtomicReference.get());
    }

}

@Data
@NoArgsConstructor
@AllArgsConstructor
class User {
    private String name;
    private Integer age;
}
