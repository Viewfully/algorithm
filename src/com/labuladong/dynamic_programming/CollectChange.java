package com.labuladong.dynamic_programming;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName: CollectChange
 * @Author: Jack.Zhang
 * @Description: 凑零钱问题
 * @Date: 2021-12-17
 */
public class CollectChange {

    // 零钱数组
    private final static int[] COINS = new int[]{1, 2, 5, 10, 20, 50, 100};
    // 指定数量的钱，对应的最小零钱数
    private final static Map<Integer, Integer> MINIMUM_AMOUNT_MAPPING = new HashMap<>();

    /**
     * 问题描述：
     *      给你 k 种面值的硬币，面值分别为 c1, c2 ... ck，每种硬币的数量无限，
     *      再给一个总金额 amount，问你最少需要几枚硬币凑出这个金额，如果不可能凑出，算法返回 -1。
     * 状态转移方程：
     *           | -1 , n < 0;
     *      f(n)<  0  , n = 0;
     *           \ min[dp(n-coin)+1] ,n>0 & coin∈coins;
     *
     *
     * */

    public static void main(String[] args) {
        System.out.println(violentRecursion(66));
        System.out.println("ppp");
        System.out.println(violentRecursionOpt(66));
        System.out.println("ppp");
        System.out.println(dynamicProgramming(66));

    }


    /**
     * <p>
     *    使用动态规划方式获取最小零钱数量
     *    主题思想：
     *          计算0->{@code target}中所有总钱数的最小零钱个数，并存放在数组中，
     *          这样在后面时，大的零钱数分解后，最小零钱数可直接从数组中获取
     *          例如：
     *             获取dp[11]的最小零钱数，是可拆分为dp[1],dp[10],则dp[11]=dp[1]+dp[10]
     * <p/>
     * @param target  总钱数
     * @return int  最少零钱个数
     * @author Jack.Zhang 2021/12/17 15:53
     * @since 1.0.0
     * @see #violentRecursionOpt(int) 请先看使用字典方式优化暴力递归算法
     */
    public static int dynamicProgramming(int target) {
        // base case
        if (target == 0) return 0;
        if (target < 0) return -1;
        int[] temp = new int[target + 1];
        // 注意，如果这里数组内元素不是最大值，结果会出错。
        for (int i = 0; i < temp.length;)
            temp[i++] = Integer.MAX_VALUE;
        temp[0] = 0;
        for (int i = 0; i < temp.length; i++) {
            for (int coin : COINS) {
                if (i - coin < 0) continue;
                temp[i] = Math.min(temp[i], 1 + temp[i - coin]);
            }
        }
        return temp[target];
    }


    /**
     * <p>
     *    使用暴力递归方式获取最少零钱个数
     *    时间复杂度：
     *          是 O(n^k)，总之是指数级别的。每个子问题中含有一个 for 循环，复杂度为 O(k)。
     *          所以总时间复杂度为 O(k * n^k)，指数级别  （k为零钱数组大小 {@link #COINS length}）
     * <p/>
     * @param target  总钱数
     * @return int 需要最少零钱个数
     * @author Jack.Zhang 2021/12/17 15:19
     * @since 1.0.0
     */
    public static int violentRecursion(int target) {
        // base case
        if (target == 0) return 0;
        if (target < 0) return -1;
        // 如果字典中存在当前总数量的最小零钱数，则直接返回不再进行递归
        if (Objects.nonNull(MINIMUM_AMOUNT_MAPPING.get(target)))
            return MINIMUM_AMOUNT_MAPPING.get(target);
        // 求最小值，这里使用正无穷,res为每次运算获得的最少零钱数
        int res = Integer.MAX_VALUE;
        for (int coin : COINS) {
            // subResult为当前子问题获取的最少零钱数量
            int subResult = violentRecursion(target - coin);
            if (subResult == -1) continue;
            // 因为在for循环已经使用了一个零钱，所以需要将零钱数加一
            res = Math.min(res, subResult + 1);
        }
        MINIMUM_AMOUNT_MAPPING.put(target, res == Integer.MAX_VALUE ? -1 : res);
        return MINIMUM_AMOUNT_MAPPING.get(target);
    }



    /**
     * <p>
     *    使用暴力递归方式获取最少零钱个数,使用字典进行优化
     * <p/>
     * @param target  总钱数
     * @return int 需要最少零钱个数
     * @author Jack.Zhang 2021/12/17 15:19
     * @since 1.0.0
     * @see #violentRecursion(int)  请先看未使用字典进行优化的暴力递归
     */
    public static int violentRecursionOpt(int target) {
        // base case
        if (target == 0) return 0;
        if (target < 0) return -1;
        // 求最小值，这里使用正无穷,res为每次运算获得的最少零钱数
        int res = Integer.MAX_VALUE;
        for (int coin : COINS) {
            // subResult为当前子问题获取的最少零钱数量
            int subResult = violentRecursion(target - coin);
            if (subResult == -1) continue;
            // 因为在for循环已经使用了一个零钱，所以需要将零钱数加一
            res = Math.min(res, subResult + 1);

        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

}
