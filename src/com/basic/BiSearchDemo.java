package com.basic;


import com.utils.ArrayGenerateUtil;

/**
 * @ClassName: BiSearchDemo
 * @Author: Jack.Zhang
 * @Description: #二分查找 代码演示
 * @Date: 2021-12-21
 */
public class BiSearchDemo {




    /**
     * 二分查找法查找关键字，若关键字不存在则返回-1，否则返回其下标
     * 输入数组前需要保证数组是从小到大排序后的数组
     * @param array 目标数组
     * @param target 目标值
     * @return 数组角标
     */
    public static int biSearch(int[] array,int target) {
        int start = 0;
        int end = array.length - 1;
        while (start <= end) {
            int mid = start + end >> 1;
            if (array[mid] == target) {
                return mid;
            }
            if (array[mid] > target) {
                end = mid - 1;
            }
            if (array[mid] < target) {
                start = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] ints = ArrayGenerateUtil.generateIntArray(true);
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i]+"\t");
        }
        System.out.println();
        System.out.println(biSearch(ints,55));
    }


}
