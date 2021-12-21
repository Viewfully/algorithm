package com.utils;


import java.util.Arrays;

/**
 * @ClassName: ArrayGenerateUtil
 * @Author: Jack.Zhang
 * @Description: 数组生成工具，即：{@code 数组自定义生成工具}
 * @Date: 2021-12-21
 */
public class ArrayGenerateUtil {

    private ArrayGenerateUtil(){}

    /** 数组生成元素数量 */
    private final static int GENERATE_COUNT = 100;

    /** 生成数组的最大值，数组范围为0=<array_value< {@link #MAXVALUE}*/
    private final static int MAXVALUE = 10000;


    /**
     * 生成有序的整形数组
     * @param isAsc 是否为正序， {@code true} 时为正序，即从小到大排序，反之为从大到小排序
     * @return 返回为整形数组
     */
    public static int[] generateIntArray(boolean isAsc){
        int[] arr = new int[GENERATE_COUNT];
        for (int i = 0; i < GENERATE_COUNT; i++) {
            arr[i] = (int) (MAXVALUE * Math.random());
        }
        Arrays.sort(arr);
        if (isAsc) {
            // 正序
            return arr;
        } else {
            return evertInt(arr);
        }
    }


    /**
     * 生成有序的小数数组
     * @param isAsc 是否为正序， {@code true} 时为正序，即从小到大排序，反之为从大到小排序
     * @return 返回为双精度浮点型数组
     */
    public static double[] generateDoubleArray(boolean isAsc){
        double[] arr = new double[GENERATE_COUNT];
        for (int i = 0; i < GENERATE_COUNT; i++) {
            arr[i] = MAXVALUE * Math.random();
        }
        Arrays.sort(arr);
        if (isAsc) {
            // 正序
            return arr;
        } else {
            return evertDouble(arr);
        }
    }


    /**
     *  翻转整形数组
     * @param source 原数组
     * @return 反转后的整形数组
     */
    private static int[] evertInt(int[] source) {
        int[] resultArr = new int[source.length];
        for (int i = 0; i < source.length; i++) {
            resultArr[i] = source[source.length - 1 - i];
        }
        return resultArr;
    }


    /**
     *  翻转浮点型数组
     * @param source 源数组
     * @return 翻转后的双浮点型数组
     */
    private static double[] evertDouble(double[] source) {
        double[] resultArr = new double[source.length];
        for (int i = 0; i < source.length; i++) {
            resultArr[i] = source[source.length - 1 - i];
        }
        return resultArr;
    }



}
