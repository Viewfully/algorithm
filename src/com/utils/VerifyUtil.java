package com.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @ClassName: VerifyUtil
 * @Author: Jack.Zhang
 * @link <a href = "mailto:zhangyue26@hikvision.com.cn">联系作者<a/>
 * @Description: 验证工具，验证算法排序准确性工具类，即：{@code 对数器}
 * @Date: 2021-11-22
 */
public class VerifyUtil {
    private VerifyUtil() {
    }

    /**
     * 设置验证次数，默认情况下验证十万次，可根据情况进行修改
     */
    private static final int VERIFY_COUNT = 10 * 100 * 100;
    /**
     * 每次生成的结果数量,默认为100个
     */
    private static final int GENERATE_COUNT = 100;
    /**
     * 生成最大数值
     */
    private static final int GENERATE_MAX_VALUE = 1000;

    /**
     * <p>
     * 验证排序方法是否正确，使用十万次比对验证排序方法是否正确
     * <p/>
     *
     * @param sortFunction 排序方法 带返回值方法，该方法返回排序后的结果
     * @param isAsc        是否为正序
     * @param isFloat      是否为浮点数
     * @return boolean  返回 {@code sortFunction} 排序方法是否可行
     * @author Jack.Zhang 2021/11/22 14:14
     * @link <a href = "mailto:zhangyue26@hikvision.com.cn">联系作者<a/>
     * @since 1.0.0
     */
    public static boolean verify(final Function<List<Number>, List<Number>> sortFunction,
                                 boolean isAsc, boolean isFloat) {
        for (int i = 0; i < VERIFY_COUNT; i++) {
            // 1.生成数组
            List<Number> generationDataList = IntStream.rangeClosed(1, GENERATE_COUNT).parallel().mapToObj(v -> {
                return isFloat ? (GENERATE_MAX_VALUE * Math.random()) : (int) (GENERATE_MAX_VALUE * Math.random());
            }).collect(Collectors.toList());
            // 2.算法排序得出结果
            List<Number> sortResult = sortFunction.apply(generationDataList);
            // 3.正确的排序结果
            List<Number> successAscList = generationDataList.stream()
                    .sorted().collect(Collectors.toList());
            if (!isAsc)
                Collections.reverse(successAscList);
            boolean success = compareTwoSets(sortResult, successAscList);
            if (!success) {
                // 自己写的算法排序结果错误
                System.out.println("自己写的算法排序结果为：\r\n"+sortResult);
                System.out.println("正确排序结果为：\r\n"+successAscList);
                return success;
            }
        }
        return true;
    }


    /**
     * <p>
     * 验证排序方法是否正确，使用十万次比对验证排序方法是否正确
     * <p/>
     *
     * @param sortFunction 排序方法 带返回值方法，该方法返回排序后的结果
     * @param isAsc        是否为正序
     * @return boolean  返回 {@code sortFunction} 排序方法是否可行
     * @author Jack.Zhang 2021/11/22 14:14
     * @link <a href = "mailto:zhangyue26@hikvision.com.cn">联系作者<a/>
     * @since 1.0.0
     */
    public static boolean verify(final Function<int[], int[]> sortFunction,
                                 boolean isAsc) {
        for (int i = 0; i < VERIFY_COUNT; i++) {
            int[] generateArr = new int[GENERATE_COUNT];
            List<Integer> generateList = new ArrayList<>(GENERATE_COUNT);
            for (int j = 0; j < GENERATE_COUNT; j++) {
                generateArr[j] = (int) (GENERATE_MAX_VALUE * Math.random());
                generateList.add(generateArr[j]);
            }
            int[] sortResults = sortFunction.apply(generateArr);
            List<Integer> sortList = new ArrayList<>(GENERATE_COUNT);
            for (int sortResult : sortResults) {
                sortList.add(sortResult);
            }
            Collections.sort(generateList);
            if (!isAsc)
                Collections.reverse(generateList);
            boolean success = compareTwoSets(sortList, generateList);
            if (!success) {
                System.out.println("自己写的算法排序结果为：\r\n"+sortList);
                System.out.println("正确排序结果为：\r\n"+generateList);
                return success;
            }
        }
        return true;
    }


    /***
     * <p>
     *    比较两个集合排序结果是否相同
     * <p/>
     * @param sortList 自己写的算法排序结果
     * @param successList  正确的算法排序结果
     * @return boolean 两集合排序结果是否相同
     * @author Jack.Zhang 2021/11/22 14:48
     * @link <a href = "mailto:zhangyue26@hikvision.com.cn">联系作者<a/>
     * @since 1.0.0
     * @see
     */
    private static boolean compareTwoSets(List<? extends Number> sortList, List<? extends Number> successList) {
        if (sortList == null && successList == null) return true;
        if (sortList == null || successList == null) return false;
        if (sortList.size() != successList.size()) return false;
        for (int i = 0; i < sortList.size(); i++) {
            if (sortList.get(i).intValue() != successList.get(i).intValue()) {
                return false;
            }

        }
        return true;
    }

}
