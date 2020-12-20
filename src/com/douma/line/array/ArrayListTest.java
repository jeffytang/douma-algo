package com.douma.line.array;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class ArrayListTest {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>(10);
        System.out.println(arrayList.isEmpty());

        arrayList.addFirst(34);
        arrayList.addLast(23);
        arrayList.add(2, 50);
        System.out.println(arrayList.isEmpty());

        System.out.println(arrayList);

        arrayList.remove(1);
        System.out.println(arrayList);

        arrayList.removeElement(1);
        System.out.println(arrayList);

        ArrayList<String> arrayList1 = new ArrayList<>(10);
        arrayList1.add(0, "hello");
        arrayList1.addLast("world");
        System.out.println(arrayList1);
    }
}
