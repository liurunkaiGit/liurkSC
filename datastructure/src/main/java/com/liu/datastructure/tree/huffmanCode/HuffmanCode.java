package com.liu.datastructure.tree.huffmanCode;

import java.io.*;
import java.util.*;

/**
 * @Description: 赫夫曼编码，解码：字符串、文件压缩解压
 * @author: liurunkai
 * @Date: 2020/2/22 17:20
 */
public class HuffmanCode {
    public static void main(String[] args) {
        // 测试压缩文件
//        String sourceFile = "d://1.png";
//        String dstFile = "d://dst.zip";
//        zipFile(sourceFile, dstFile);
        // 测试解压
        String zipFile = "d://dst.zip";
        String dstFile = "d://2.png";
        unZipFile(zipFile, dstFile);
//        String str = "i like like like java do you like a java";
//        // 压缩
//        Byte[] huffmanCodeBytes = zip(str.getBytes());
//        System.out.println(Arrays.toString(huffmanCodeBytes));
//        // 4. 解压
//        HashMap<Byte, String> huffmanCodes = createHuffmanCode(str.getBytes());
//        byte[] bytes = huffmanUnZip(huffmanCodes, huffmanCodeBytes);
//        System.out.println("原来的字符串" + new String(bytes));
    }

    /**
     * 文件压缩
     *
     * @param sourceFile 源文件的位置
     * @param dstFile    压缩后文件的输出位置
     */
    private static void zipFile(String sourceFile, String dstFile) {
        // 文件输入流、文件输出流和对象输出流
        FileInputStream is = null;
        FileOutputStream os = null;
        ObjectOutputStream oos = null;
        try {
            // 创建文件相关的输入流
            is = new FileInputStream(sourceFile);
            // 创建一个和源文件大小一样的byte[]
            byte[] bytes = new byte[is.available()];
            // 读取文件
            is.read(bytes);
            // 对源文件压缩
            Byte[] huffmanBytes = zip(bytes);
            // 创建文件的输出流，存放压缩文件
            os = new FileOutputStream(dstFile);
            // 创建一个和文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            // 把赫夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);
            // 这里我们以对象流的方式写入赫夫曼编码，是为了以后恢复文件使用
            // 注意：一定要把赫夫曼编码写入压缩文件
            oos.writeObject(createHuffmanCode(bytes));
        } catch (Exception e) {
            throw new RuntimeException("文件压缩异常，{}", e);
        } finally {
            try {
                oos.close();
                os.close();
                is.close();
            } catch (IOException e) {
                throw new RuntimeException("流关闭异常");
            }
        }
    }

    /**
     * 文件解压
     *
     * @param zipFile 待解压的文件
     * @param dstFile 解压到的位置
     */
    public static void unZipFile(String zipFile, String dstFile) {
        // 定义文件输入流、对象输入流、文件输出流
        FileInputStream is = null;
        ObjectInputStream ois = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(zipFile);
            // 创建一个和is关联的对象输入流
            ois = new ObjectInputStream(is);
            // 读取byte数组：huffmanBytes
            Byte[] huffmanBytes = (Byte[]) ois.readObject();
            // 读取赫夫曼编码表
            Map<Byte, String> codes = (Map<Byte, String>) ois.readObject();
            // 解码
            byte[] bytes = huffmanUnZip(codes, huffmanBytes);
            // 将bytes数组写入到目标文件
            os = new FileOutputStream(dstFile);
            // 写数据到dstFile
            os.write(bytes);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("解压异常,{}", e);
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (IOException e) {
                throw new RuntimeException("关闭流异常");
            }
        }
    }

    /**
     * 封装压缩方法
     *
     * @param bytes
     * @return
     */
    private static Byte[] zip(byte[] bytes) {
        HashMap<Byte, String> huffmanCodes = createHuffmanCode(bytes);
        // 3.完成压缩
        Byte[] huffmanCodeBytes = huffmanZip(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }

    private static HashMap<Byte, String> createHuffmanCode(byte[] bytes) {
        List<Node> nodes = getByteCount(bytes);
        // 1.创建赫夫曼树
        Node root = createHuffmanTree(nodes);
        // 2.根据赫夫曼树创建对应的赫夫曼编码
        HashMap<Byte, String> huffmanCodes = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        createHuffmanCode(root, null, huffmanCodes, stringBuilder);
        return huffmanCodes;
    }

    // 解码，需要将huffmanCodeBytes数组[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]转换成赫夫曼对应的二进制字符串"1000001001010101010..."
    // 再将赫夫曼对应的二进制字符串"1000001001010101010..."转成"i like like like java do you like a java"

    /**
     * 解压
     *
     * @param huffmanCodes 赫夫曼编码
     * @param huffmanBytes 赫夫曼编码得到的字节数组[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
     * @return 返回原来的字符串对应的数组
     */
    private static byte[] huffmanUnZip(Map<Byte, String> huffmanCodes, Byte[] huffmanBytes) {
        StringBuilder stringBuilder = new StringBuilder();
        // huffmanCodeBytes数组[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]转换成赫夫曼对应的二进制字符串"1000001001010101010..."
        for (int i = 0; i < huffmanBytes.length; i++) {
            String str;
            if (i != huffmanBytes.length - 1) {
                str = byteToBitString(true, huffmanBytes[i]);
            } else {
                str = byteToBitString(false, huffmanBytes[i]);
            }
            stringBuilder.append(str);
        }
//        System.out.println(stringBuilder.toString());
        // 把字符串10101000101111111100100010111按照指定的赫夫曼编码进行解码，把赫夫曼编码表进行调换，因为需要反向查询：原来a=100,现在要根据100查a
        Map<String, Byte> revertHuffmanCode = new HashMap<>();
        for (Map.Entry entry : huffmanCodes.entrySet()) {
            revertHuffmanCode.put((String) entry.getValue(), (Byte) entry.getKey());
        }
        System.out.println(stringBuilder.length());
        // 创建集合，存放byte
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length();) {
            int count = 1;
            Boolean flag = true;
            Byte b = null;
            while (flag) {
                // 一个一个匹配
                if (i + count <= stringBuilder.length()) {
                    String str = stringBuilder.substring(i, i + count);
                    b = revertHuffmanCode.get(str);
                    if (b == null) { //说明没有匹配到
                        count++; //count后移，重新匹配
                    } else {
                        flag = false;
                    }
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }
        System.out.println(list.size());
        // 把list数组中的数据放到Byte[]中并返回
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            bytes[i] = list.get(i);
        }
        return bytes;
    }

    /**
     * 将一个byte转成二进制字符串
     * 解码，需要将huffmanCodeBytes数组[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
     * 转换成赫夫曼对应的二进制字符串"1000001001010101010..."
     *
     * @param flag 标识是否需要补高位，如果是true，需要补高位，如果是false，标识不需要
     * @param b
     * @return
     */
    private static String byteToBitString(boolean flag, byte b) {
        // 使用变量保存b
        int temp = b;
        if (flag) { //如果是正数，存在补高位
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    /**
     * 赫夫曼压缩
     *
     * @param bytes        原始的字节数组
     * @param huffmanCodes 赫夫曼编码
     * @return 返回压缩后的字节数组
     */
    private static Byte[] huffmanZip(byte[] bytes, HashMap<Byte, String> huffmanCodes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
//        System.out.println(stringBuilder.toString());
        int length = stringBuilder.length() % 8 == 0 ? stringBuilder.length() / 8 : stringBuilder.length() / 8 + 1;
        Byte[] huffmanCodeBytes = new Byte[length];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strBytes;
            if (i + 8 > stringBuilder.length()) {
                strBytes = stringBuilder.substring(i);
            } else {
                strBytes = stringBuilder.substring(i, i + 8);
            }
            byte aByte = (byte) Integer.parseInt(strBytes, 2);
            huffmanCodeBytes[index] = aByte;
            index++;
        }
        return huffmanCodeBytes;
    }

    /**
     * 生成赫夫曼编码
     *
     * @param node          传入的节点
     * @param code          编码，向左为0，向右为1
     * @param map           存放字符及对应的路径
     * @param stringBuilder 用于字符的路径
     */
    private static void createHuffmanCode(Node node, Integer code, HashMap<Byte, String> map, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
//        if (stringBuilder.toString() != null && stringBuilder.toString() != "") {
//            stringBuilder2 = new StringBuilder(stringBuilder);
//        }
        stringBuilder2.append(code);
        if (node != null) { //传入的节点不为空
            if (node.getData() == null) { //说明是非叶子节点
                // 向左递归
                createHuffmanCode(node.getLeft(), 0, map, stringBuilder2);
                // 向右递归
                createHuffmanCode(node.getRight(), 1, map, stringBuilder2);
            } else { // 说明是存放字符的叶子节点
                map.put(node.getData(), stringBuilder2.toString().substring(4, stringBuilder2.length()));
            }
        }
    }

    private static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("赫夫曼树为空");
        }
    }

    /**
     * 构建赫夫曼树
     *
     * @param nodes
     * @return
     */
    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(null, leftNode.getWeight() + rightNode.getWeight());
            parent.setLeft(leftNode);
            parent.setRight(rightNode);
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    /**
     * 统计每个字符出现的次数，并构建node集合
     *
     * @param bytes
     * @return
     */
    private static List<Node> getByteCount(byte[] bytes) {
        Map<Byte, Integer> map = new HashMap<>();
        for (byte b : bytes) {
            if (map.get(b) != null) {
                Integer weight = map.get(b);
                map.put(b, ++weight);
            } else {
                map.put(b, 1);
            }
        }
        List<Node> nodes = new ArrayList<>();
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }
}

class Node implements Comparable<Node> {
    private Byte data; //存放字符， a=97
    private int weight; //权重，出现的次数
    private Node left;
    private Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public Byte getData() {
        return data;
    }

    public void setData(Byte data) {
        this.data = data;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        // 按照weight升序排列
        return this.weight - o.weight;
    }
}
