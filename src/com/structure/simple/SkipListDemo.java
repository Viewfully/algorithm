package com.structure.simple;

import java.util.HashMap;

/**
 * @ClassName: SkipListDemo
 * @Author: Jack.Zhang
 * @Description: 跳跃表数据结构基本实现思路 TODO 待完善
 * @Date: 2021-12-22
 */
public class SkipListDemo {

    /**
     * 实现思路（原理详见README）
     * 1、我们采用抛硬币的方式来决定新元素插入的最高层数，这当然不能在程序中实现。
     * 代码中，我们采用随机数生成的方式来获取新元素插入的最高层数。
     * 我们先估摸一下n的规模，然后定义跳跃表的最大层数maxLevel，
     * 那么底层，也就是第0层，元素是一定要插入的，概率为1；最高层，
     * 也就是maxLevel层，元素插入的概率为1/2^maxLevel。
     * 2、我们先随机生成一个范围为0~2^maxLevel-1的一个整数r。
     * 那么元素r小于2^(maxLevel-1)的概率为1/2，r小于2^(maxLevel-2)的概率为1/4，……，
     * r小于2的概率为1/2^(maxLevel-1)，r小于1的概率为1/2^maxLevel。
     * 3、举例，假设maxLevel为4，那么r的范围为0~15，则r小于8的概率为1/2，r小于4的概率为1/4，
     * r小于2的概率为1/8，r小于1的概率为1/16。1/16正好是maxLevel层插入元素的概率，
     * 1/8正好是maxLevel层插入的概率，以此类推。
     * 4、通过这样的分析，我们可以先比较r和1，如果r<1，那么元素就要插入到maxLevel层以下；
     * 否则再比较r和2，如果r<2，那么元素就要插入到maxLevel-1层以下；
     * 再比较r和4，如果r<4，那么元素就要插入到maxLevel-2层以下……如果r>2^(maxLevel - 1)，
     * 那么元素就只要插入在底层即可。
     **/

    /**
     * 跳跃表实现,非线程安全,从小到大排序,使用抛硬币方式(这种抛硬币方式概率为1/4)判定新加入的节点是否升级为索引
     * */
    private static class SkipList<T extends Comparable<? super T>>{
        // 最大限制索引级别
        private final static int MAX_LEVEL_FILTER = 1 << 4;
        // 最大索引级别
        private int maxLevel;
        // 数据链表的长度
        private int length;
        // 实际数据链表的头节点
        private SkipNode<T> dataHead;
        // 提升为上一级索引的概率,该值为0<chance<=1的小数,否则为0.25
        private double chance = 0.25;
        // 用于存放每一级索引的头节点
        private HashMap<Integer, SkipNode<T>> levelHeadMap = new HashMap<>(MAX_LEVEL_FILTER);


        public <T extends Comparable<? super T>> SkipList() {
            maxLevel = 1;
            chance = 0.25;
        }

        public SkipList(int maxLevel,int chance){
            if (maxLevel>MAX_LEVEL_FILTER)
                maxLevel = MAX_LEVEL_FILTER;
            this.maxLevel = maxLevel;
            if (chance > 0 && chance <= 1)
                this.chance = chance;
            for (int i = 1; i <= maxLevel; i++) {
                levelHeadMap.put(i, new SkipNode<T>());
            }
        }

        /***
         * <p>
         *    决策当前结点提升为上一级索引
         * <p/>
         * @return boolean
         * @author Jack.Zhang 2021/12/22 17:39
         * @since 1.0.0
         */
        private boolean policy() {
            return Math.random() <= chance;
        }


        /**
         * <p>
         *    添加节点
         * <p/>
	     * @param node  加入的节点
         * @return void
         * @author Jack.Zhang 2021/12/22 17:46
         * @since 1.0.0
         */
        public void insert(SkipNode<T> node) throws IllegalArgumentException{
            // 长度加一
            ++length;
            // 如果头结点为空，则添加直接添加结点
            if (dataHead == null) {
                dataHead = node;
                return;
            }
            // 否则需要进行比较获取该节点的前驱节点，注意：当前节点最开始节点一定要是实际数据链表的头结点
            SkipNode<T> preNode = findPre(node);
            // 将该节点直接添加到当前节点的后方
            node.nextNode = preNode.nextNode;

        }

        /**
         * <p>
         *    寻找合适的合适位置的前驱结点
         *    注意：当前节点最开始节点一定要是实际数据链表的头结点
         * <p/>
	     * @param node  结点
         * @return com.structure.simple.SkipListDemo.SkipNode<T>
         * @author Jack.Zhang 2021/12/22 17:56
         * @since 1.0.0
         */
        private SkipNode<T> findPre(SkipNode<T> node) {

        }



    }



    /**
     * 跳跃表结点
     * */
    private static class SkipNode<T>{
        // 结点存储的实际值
        private T value;
        // 指向下一层节点元素
        private SkipNode<T> nextNode;
        // 下一层结点元素，指向的元素值应与该元素值相等
        private SkipNode<T> nextLevelNode;


        public SkipNode() {
        }

        public SkipNode(T value){
            if (value == null)
                throw new NullPointerException("The node value cannot be empty !!!");
            this.value = value;
        }

        public SkipNode(T value,SkipNode<T> nextNode){
            this.value = value;
            this.nextNode = nextNode;
        }

        public SkipNode(T value,SkipNode<T> nextNode,SkipNode<T> nextLevelNode){
            this.value = value;
            this.nextNode = nextNode;
            this.nextLevelNode = nextLevelNode;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public SkipNode<T> getNextNode() {
            return nextNode;
        }

        public void setNextNode(SkipNode<T> nextNode) {
            this.nextNode = nextNode;
        }

        public SkipNode<T> getNextLevelNode() {
            return nextLevelNode;
        }

        public void setNextLevelNode(SkipNode<T> nextLevelNode) {
            this.nextLevelNode = nextLevelNode;
        }
    }
}