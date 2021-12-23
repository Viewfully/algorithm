package com.other.limiting;

/**
 * @ClassName: LeakyBucketDemo
 * @Author: Jack.Zhang
 * @Description: 漏桶算法简单实现思路，这只是一个简单的示例，应用过程中需要根据实际需求开发
 * @Date: 2021-12-23
 */
public class LeakyBucketDemo {

    static long timeStamp = System.nanoTime();
    static int capacity = 10000;// 桶的容量
    static int rate = 1;//水漏出的速度
    static long water = 100L;//当前水量


    /**
     * <p>
     *    判断是否还能加水
     * <p/>
     * @return boolean 是否达到了能够处理的请求缓冲上限(即：是否还能再加水)
     * @author Jack.Zhang 2021/12/23 13:43
     * @since 1.0.0
     */
    public static boolean control() {
        //先执行漏水，因为rate是固定的，所以可以认为“时间间隔*rate”即为漏出的水量
        long  now = System.nanoTime();
        water = Math.max(0, water - (now - timeStamp) * rate);
        timeStamp = now;

        if (water < capacity) { // 水还未满，加水
            water ++;
            return true;
        } else {
            return false;//水满，拒绝加水
        }
    }
}
