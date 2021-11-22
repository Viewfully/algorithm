package com.basic;

import com.utils.VerifyUtil;

/**
 * @ClassName: SelectSortDemo
 * @Author: Jack.Zhang
 * @link <a href = "mailto:zhangyue26@hikvision.com.cn">联系作者<a/>
 * @Description:  #选择排序算法  代码演示
 * @Date: 2021-11-22
 */
public class SelectSortDemo {


    /**
     * <p>
     *    选择算法逻辑代码。时间最优时间复杂度为 O(n^2),最坏负杂度为O(n^2),空间复杂度为O(1)
     *    TODO 注意选择排序与插入排序的不同，
     *    选择排序是在i位置后选择第i个最小/大的值插入到i位置，
     *    插入排序是将第i位值向前移动，类似与冒泡，找到合适的位置，使得i位置之前数据是有序的。
     * <p/>
     * @param arr
     * @return int[]
     * @author Jack.Zhang 2021/11/22 15:25
     * @link <a href = "mailto:zhangyue26@hikvision.com.cn">联系作者<a/>
     * @since 1.0.0
     * @see
     */
    public static int[] swap(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            // 默认初始化时认定最小值为第一个元素
            // 循环向后遍历，得出最小值，并将最小值设置为第i位
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    // 如果第i位值大于第j位值，则将其交换，保证i始终为最小值
                    // 由于使用异或算法，并未使用多余的空间，则空间复杂度为 O(1)
                    // 注：起始使用有限个数的临时变量的空间复杂度均可视为 O(1)
                    // 但是在使用异或交换元素时首先必须、必须、必须要保证i与j是永远不相等的，如果出现i=j的情况，那么会生成另外一个数值
                    arr[i] = arr[i] ^ arr[j];
                    arr[j] = arr[i] ^ arr[j];
                    arr[i] = arr[i] ^ arr[j];
                }
            }
        }
        return arr;
    }


    // 验证
    public static void main(String[] args) {
        boolean verify = VerifyUtil.verify(SelectSortDemo::swap, true);
        System.out.println(verify);
    }
}
