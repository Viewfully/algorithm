package com.basic;

import com.utils.VerifyUtil;

/**
 * @ClassName: BubbleSortDemo
 * @Author: Jack.Zhang
 * @Description: #冒泡排序 代码演示
 * @Date: 2021-11-22
 */
public class BubbleSortDemo {

    /**
     * <p>
     *    冒泡排序，时间复杂度为 O(n^2) 空间复杂度为 O(1)
     * <p/>
	 * @param arr  输入无序数组
     * @return int[]  返回有序数组，从小到大排序
     * @author Jack.Zhang 2021/11/22 15:35
     * @since 1.0.0
     */
    public static int[] bubble(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j+1]) {
//                    int temp = arr[j];
//                    arr[j] = arr[j + 1];
//                    arr[j + 1] = temp;
                    arr[j] = arr[j] ^ arr[j + 1];
                    arr[j + 1] = arr[j] ^ arr[j + 1];
                    arr[j] = arr[j] ^ arr[j + 1];
                }
            }
        }
        return arr;
    }

    // 验证
    public static void main(String[] args) {
        boolean verify = VerifyUtil.verify(BubbleSortDemo::bubble, true);
        System.out.println(verify);
    }
}
