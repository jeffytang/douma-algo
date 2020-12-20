package com.douma.line.algo.sort.train;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class _75_SortColors {

    // 三路快排 (一趟扫描)
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public void sortColors(int[] nums) {
        int zero = 0;
        int two = nums.length - 1;

        int i = 0;
        while (i <= two) {
            if (nums[i] == 0) {
                swap(nums, i, zero);
                zero++;
                i++;
            } else if (nums[i] == 2) {
                swap(nums, i, two);
                two--;
            } else {
                i++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    // 计数排序 (两趟扫描)
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public void sortColors1(int[] nums) {
        // 1. 计数
        int[] count = new int[3];
        for (int num : nums) {
            count[num]++;
        }

        // 2. 排序
        int k = 0;
        for (int i = 0; i <= 2; i++) {
            int num = count[i];
            for (int j = 1; j <= num; j++) {
                nums[k++] = i;
            }
        }
    }
}
