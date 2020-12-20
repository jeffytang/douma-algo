package com.douma.tree;

import com.douma.tree.avl.AVLTree;
import com.douma.tree.bst.BST;
import com.douma.tree.rb.RBTree;

import java.util.ArrayList;
import java.util.Random;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class TreePerformanceTest1 {
    private static Random random = new Random();
    public static void main(String[] args) {
        int num = 20000000;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(random.nextInt());
        }

        long startTime = System.nanoTime();
        BST<Integer> bst = new BST<>();
        for (Integer i : list) bst.add(i);
        long endTime = System.nanoTime();
        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("BST：" + time + " s");

        startTime = System.nanoTime();
        AVLTree<Integer> avl = new AVLTree<>();
        for (Integer i : list) avl.add(i);
        endTime = System.nanoTime();
        time = (endTime - startTime) / 1000000000.0;
        System.out.println("AVL：" + time + " s");

        startTime = System.nanoTime();
        RBTree<Integer> rbTree = new RBTree<>();
        for (Integer i : list) rbTree.add(i);
        endTime = System.nanoTime();
        time = (endTime - startTime) / 1000000000.0;
        System.out.println("RB：" + time + " s");
    }

    /*
    BST：40.023997 s
    AVL：126.4299055 s
    RB：68.014194 s
     */
}
