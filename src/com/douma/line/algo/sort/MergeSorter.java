package com.douma.line.algo.sort;

import java.util.Arrays;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class MergeSorter {
    public void sort(int[] data) {
        if (data == null || data.length < 2) return;
        int[] tmp = new int[data.length];
        sort(data, 0, data.length - 1, tmp); // 大问题
    }
    // 给子数组进行排序，子问题
    private void sort(int[] data, int left, int right, int[] tmp) {
        // 终止递归条件
        if (left == right) return;
        // 分别对两个子问题求解
        int mid = (left + right) / 2;
        sort(data, left, mid, tmp);
        sort(data, mid + 1, right, tmp);
        // 合并两个有序的数组，即合并 [left...mid] 和 [mid + 1, right]
        // [left...mid] 和 [mid + 1, right] 这两个数组在上面已经排过序了
        merge2(data, left, mid, right, tmp);
    }

    private void merge(int[] data, int left, int mid, int right, int[] tmp) {
        int tmpPos = left;
        int i = left;
        int j = mid + 1;
        // 将左边和右边的元素按照顺序拷贝到临时的数组中
        while (i <= mid && j <= right) {
            if (data[i] <= data[j]) {
                tmp[tmpPos++] = data[i++];
            } else { // data[i] > data[j]
                tmp[tmpPos++] = data[j++];
            }
        }
        // 如果左边还有元素，则直接将左边的元素拷贝到临时数组
        while (i <= mid) {
            tmp[tmpPos++] = data[i++];
        }
        // 如果右边还有元素，则直接将右边的元素拷贝到临时数组
        while (j <= right) {
            tmp[tmpPos++] = data[j++];
        }
        // 拷贝
        for (tmpPos = left; tmpPos <= right; tmpPos++) {
            data[left++] = tmp[tmpPos];
        }
    }

    private void merge2(int[] data, int left, int mid, int right, int[] tmp) {
        for (int i = left; i <= right; i++) {
            tmp[i] = data[i];
        }

        int i = left;
        int j = mid + 1;
        for (int k = left; k <= right; k++) {
            if (i == mid + 1) { // 左边没有元素，右边有元素
                data[k] = tmp[j++];
            } else if (j == right + 1) { // 左边有元素，右边没有元素
                data[k] = tmp[i++];
            } else if (tmp[i] <= tmp[j]) {
                data[k] = tmp[i++];
            } else {
                // bug 修复：这个是 tmp[j++]
                data[k] = tmp[j++];
            }
        }
    }

    public void sortBU(int[] data) {
        if (data == null || data.length <= 1) return;
        int len = data.length;
        int[] tmp = new int[len];
        for (int size = 1; size < len; size += size) { // size 表示子数组的长度，1,2,4,8
            for (int left = 0; left < len - size; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min(left + 2 * size - 1, len - 1);
                merge(data, left, mid, right, tmp);
            }
        }
    }

    public static void main(String[] args) {
        int[] data = new int[]{34, 33, 12, 78, 21, 1, 98, 100};
        new MergeSorter().sortBU(data);
        System.out.println(Arrays.toString(data));
    }
}
