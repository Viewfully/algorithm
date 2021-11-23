package com.basic;

import com.utils.VerifyUtil;

/**
 * @ClassName: MergeSortDemo
 * @Author: Jack.Zhang
 * @link <a href = "mailto:zhangyue26@hikvision.com.cn">联系作者<a/>
 * @Description: #归并排序 代码演示
 * @Date: 2021-11-22
 */
public class MergeSortDemo {


    /**
     * <p>
     *    归并排序,时间复杂度为 O(N*logN) 空间复杂度为O(N)
     *    TODO Master 表达式推导归并排序时间复杂度
     *    T(N) = a*T(N/b) + O(N^d)
     *        1) log(b,a) > d 时间复杂度为 O(N^log(b,a))
     *        2) log(b,a) = d 时间复杂度为 O(N^d * logN)
     *        3) log(b,a) < d 时间复杂度为 O(N^d)
     *    归并排序可通过该表达式推出时间复杂度
     *    仅查看递归方法 {@link #process} 的一层即可，不需要看递归多层计算
     *    可得 Master 表达式为
     *    T(N) = 2 * T(N/2) + O(N^1)
     *    a = 2, b = 2,d = 1
     *    log(2,2) = 1
     *    可得出时间复杂度为 O(N * logN)
     * <p/>
	 * @param arr
     * @return int[]
     * @author Jack.Zhang 2021/11/23 13:53
     * @link <a href = "mailto:zhangyue26@hikvision.com.cn">联系作者<a/>
     * @since 1.0.0
     * @see
     */
    public static int[] mergeSort(int[] arr) {
        if (arr == null || arr.length == 0) return arr;
        int mid = arr.length >> 1;
        int end = arr.length - 1;
        process(arr, 0, mid);
        process(arr, mid + 1, end);
        merge(arr, 0, end, mid);
        return arr;
    }

    private static void process(int[] arr, int left, int right) {
        if (arr == null || arr.length == 0) return;
        if (left < right) {
            int mid = left + ((right - left) >> 1);
            process(arr, left, mid);
            process(arr, mid + 1, right);
            merge(arr, left, right, mid);
        }
    }

    private static void merge(int[] arr, int start, int end, int mid) {
        if (start == end)return;
        int[] temp = new int[end - start + 1];
        int leftArrIndex = start;
        int rightArrIndex = mid + 1;
        int index = 0;
        while (leftArrIndex <= mid && rightArrIndex <= end)
            temp[index++] = arr[leftArrIndex] > arr[rightArrIndex] ? arr[rightArrIndex++] : arr[leftArrIndex++];
        while (leftArrIndex <= mid)
            temp[index++] = arr[leftArrIndex++];
        while (rightArrIndex <= end)
            temp[index++] = arr[rightArrIndex++];
        for (int i = start, j = 0; i <= end; i++) {
            arr[i] = temp[j++];
        }
    }


    public static void main(String[] args) {
        boolean verify = VerifyUtil.verify(MergeSortDemo::mergeSort, true);
        System.out.println(verify);

    }
}
