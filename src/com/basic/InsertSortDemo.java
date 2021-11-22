package com.basic;

import com.utils.VerifyUtil;

/**
 * @ClassName: InsertSortDemo
 * @Author: Jack.Zhang
 * @link <a href = "mailto:zhangyue26@hikvision.com.cn">联系作者<a/>
 * @Description: #插入排序 代码演示
 * @Date: 2021-11-22
 */
public class InsertSortDemo {


    /**
     * <p>
     *    插入排序代码实现
     * <p/>
	 * @param arr  未排序数组
     * @return int[]  排序后数组
     * @author Jack.Zhang 2021/11/22 16:03
     * @link <a href = "mailto:zhangyue26@hikvision.com.cn">联系作者<a/>
     * @since 1.0.0
     * @see
     */
    public static int[] insert(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            // 每次排序完成前i位都是有序的,所以一旦交换完成，arr[j] >= arr[j - 1] 时，那么在j->0之间的都是小于单签arr[j]的
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    arr[j - 1] = arr[j - 1] ^ arr[j];
                    arr[j] = arr[j - 1] ^ arr[j];
                    arr[j - 1] = arr[j - 1] ^ arr[j];
                }else {
                    // 效率优化
                    break;
                }
            }
        }
        return arr;
    }

    // 验证
    public static void main(String[] args) {
        boolean verify = VerifyUtil.verify(InsertSortDemo::insert, true);
        System.out.println(verify);
    }
}
