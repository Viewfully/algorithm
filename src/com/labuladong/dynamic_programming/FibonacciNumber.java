package com.labuladong.dynamic_programming;

import java.util.HashMap;
import java.util.Objects;

/**
 * @ClassName: FibonacciNumber
 * @Author: Jack.Zhang
 * @Description: 斐波那契数求解
 * @Date: 2021-12-17
 */
public class FibonacciNumber {


    /** 使用字典将已经计算过的数据进行记录 **/
    private static HashMap<Integer, Integer> DICTIONARY_MAP = new HashMap<>();



    /**
     * 指南：
     *      求解斐波那契数序列
     *        1	 1	2	3	5	8	13	21	34	55 ... ...
     *      状态转移方程：
     *                   | 1 , n = 1,2 ;
     *              f(n)<
     *                   \ f(n-1)+f(n-2), n > 2 ;
     *      方法查看顺序(从简->难)
     *         -> {@link #basicRecursion(int)}  暴力递归解法
     *         -> {@link #pruningRecursion(int)} 附带字典的暴力递归解法
     *         -> {@link #dynamicProgramming(int)} 动态规划解法（未优化）
     *         -> {@link #dynamicProgrammingOpt(int)} 优化后的动态规划解法
     * */



    /**
     * <p>
     * 求解斐波那契数序列
     *   1	 1	2	3	5	8	13	21	34	55 ... ...
     * <p/>
     *
     * @return void
     * @author Jack.Zhang 2021/12/17 13:59
     * @see
     * @since 1.0.0
     */
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            System.out.print(basicRecursion(i)+"\t");
        }
        System.out.println();
        for (int i = 1; i <= 10; i++) {
            System.out.print(pruningRecursion(i)+"\t");
        }
        System.out.println();
        for (int i = 1; i <= 10; i++) {
            System.out.print(dynamicProgramming(i)+"\t");
        }
        System.out.println();
        for (int i = 1; i <= 10; i++) {
            System.out.print(dynamicProgrammingOpt(i)+"\t");
        }
        System.out.println();

    }


    /**
     * <p>
     *      斐波那契数基础解法，暴力递归
     *      时间复杂度为O(2^n)
     * <p/>
     * @param num 需要求解{@code num}的斐波那契数
     * @return int
     * @author Jack.Zhang 2021/12/17 13:55
     * @since 1.0.0
     */
    public static int basicRecursion(int num) {
        // basic case
        if (num<1) return 0;
        if (num <= 2) return 1;
        return basicRecursion(num - 1) + basicRecursion(num - 2);
    }


    /**
     * <p>
     *    对 {@link #basicRecursion} 暴力递归方法进行剪枝，
     *    使用哈希表的方式进行剪枝，获取已经计算过的数据，避免
     *    重新计算造成浪费
     *    时间复杂度为O(n) 空间复杂度为O(n)
     * <p/>
     * @param num 需要求解{@code num}的斐波那契数
     * @return int
     * @author Jack.Zhang 2021/12/17 14:00
     * @since 1.0.0
     * @see #basicRecursion 基础解法
     */
    public static int pruningRecursion(int num) {
        // base case
        if (num<1) return 0;
        if (num <= 2) return 1;
        // pruning 剪枝
        if (Objects.nonNull(DICTIONARY_MAP.get(num))) return DICTIONARY_MAP.get(num);
        DICTIONARY_MAP.put(num, pruningRecursion(num - 1) + pruningRecursion(num - 2));
        return DICTIONARY_MAP.get(num);
    }


    /**
     * <p>
     *      使用动态规划自底向上进行计算，
     *      方法 {@link #basicRecursion(int)} 与 {@link #pruningRecursion(int)}
     *      使用的是自顶向下思想，使用动态规划时间复杂度为O(n),空间复杂度为O(n)
     * <p/>
     * @param num  需要求解{@code num}的斐波那契数
     * @return int
     * @author Jack.Zhang 2021/12/17 14:09
     * @since 1.0.0
     * @see #pruningRecursion 剪枝解法
     */
    public static int dynamicProgramming(int num) {
        // base case
        if (num<1) return 0;
        if (num <= 2) return 1;
        int[] temp = new int[num + 1];
        temp[1] = temp[2] = 1;

        for (int i = 3; i <= num; i++) {
            temp[i] = temp[i - 1] + temp[i - 2];
        }
        return temp[num];
    }


    /**
     * <p>
     *    使用动态规划自底向上进行计算，优化后的动态规划
     *    时间复杂度为O(n)，空间复杂度为O(1)
     * <p/>
     * @param num
     * @return
     * @author Jack.Zhang 2021/12/17 14:19
     * @since 1.0.0
     * @see #dynamicProgramming 未优化的动态规划解法
     */
    public static int dynamicProgrammingOpt(int num) {
        // base case
        if (num<1) return 0;
        if (num <= 2) return 1;
        int pre_1 = 1, pre_2 = 1;
        int sum = 0;
        for (int i = 3; i <= num; i++) {
            sum = pre_1 + pre_2;
            pre_2 = pre_1;
            pre_1 = sum;
        }
        return sum;
    }

}
