package com.basic;


import com.utils.ArrayGenerateUtil;

/**
 * @ClassName: QuickSortDemo
 * @Author: Jack.Zhang
 * @Description: #快速排序 代码演示
 * @Date: 2021-12-23
 */
public class QuickSortDemo {


    /**
     * 快速排序原理
     * 选择一个关键词作为基准值，比基准值小的都在左边序列（一般是无序的）
     * 比基准值大的都在右边，(一般是无序的)，一般选择序列的第一个元素。
     * 一次循环：从后向前开始比较，用基准值和最后一个值比较，如果比基准值小的交换位置，
     * 如果没有继续比较下一个，直到找到第一个比基准小的值才交换，找到这个值之后，又从前往后开始比较，
     * 如果有比基准最大的，交换位置，如果没有继续比较下一个，知道找打第一个比基准值大的值才交换，
     * 直到从前往后的比较索引>从后往前比较的索引，结束第一次循环，此时，对于基准值来说，左右两边就是有序的了
     *
     * 示例说明：
     *    基准值=12  从后向前开始比较，用基准值和最后一个值比较（从45向前开始），直到遇到 1 后交换位置，第一次循环截止
     *       |
     *      12   20    5    16    15    1    30    45              <-- 原序列
     *    （第一次从后向前）第一次循环结束（从后向前结束），此时 end=5
     *①    1   20    5    16    15    12    30    45
     *    （第一次从前向后）找到第一个比基准值大的值20，交换位置
     *②    1   12    5    16    15    20    30    45
     *    （循环第二次从后向前）找到第一个比基准值小的值 2 交换
     *③    1    5    12   16    15    20    30    45
     *            .......
     *    TODO 注：后向前遍历，找大值，前向后遍历，找小值
     */


    /**
     * 快速排序
     * @param source 原数组
     * @param low 数组低索引
     * @param high 数组高索引
     */
    public static void quickSort(int[] source,int low,int high) {
        int start = low;
        int end = high;
        int key = source[low];
        while (end > start) {
            // 从后向前比较，找到第一个比基准值大的值，并交换
            while (end>start&&source[end]>=key)
                end--;
            if (source[end] <= key) {
                int temp = source[end];
                source[end] = source[start];
                source[start] = temp;
            }
            // 从前往后比较，找到第一个比基准值小的值，并交换
            while (end>start&& source[start]<=key)
                start++;
            if (source[start] >= key) {
                int temp = source[start];
                source[start] = source[end];
                source[end] = temp;
            }
            // 此时第一个循环结束，关键值的位置已经确定了，左边的值都比关键值小，右边的值都比关键字大，但是左右两边可能还不是顺序的
        }
        // 左边序列，第一个索引位置到关键值索引-1
        if (start>low) quickSort(source, low, start - 1);
        // 右边序列，从关键值索引+1到最后一个
        if (end<high) quickSort(source, end + 1, high);
    }


    public static void main(String[] args) {
        int[] array = ArrayGenerateUtil.generateRandomIntArray();
        quickSort(array, 0, array.length - 1);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "\t");
        }
        System.out.println();

    }
}
