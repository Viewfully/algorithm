package com.structure.simple;


import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName: SkipListDemo
 * @Author: Jack.Zhang
 * @Description: 跳跃表数据结构基本实现思路，代码来源于
 * https://blog.csdn.net/moakun/article/details/79997037
 * TODO 作者暂未验证准确性，仅供参考
 * @Date: --
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
     * 3、举例，假设maxLevel为4，那么r的范围为0~，则r小于8的概率为1/2，r小于4的概率为1/4，
     * r小于2的概率为1/8，r小于1的概率为1/。1/正好是maxLevel层插入元素的概率，
     * 1/8正好是maxLevel层插入的概率，以此类推。
     * 4、通过这样的分析，我们可以先比较r和1，如果r<1，那么元素就要插入到maxLevel层以下；
     * 否则再比较r和2，如果r<2，那么元素就要插入到maxLevel-1层以下；
     * 再比较r和4，如果r<4，那么元素就要插入到maxLevel-2层以下……如果r>2^(maxLevel - 1)，
     * 那么元素就只要插入在底层即可。
     **/


    private static class SkipList <T>{
        private SkipListNode<T> head,tail;
        private int nodes;//节点总数
        private int listLevel;//层数
        private Random random;// 用于投掷硬币
        private static final double PROBABILITY=0.5;//向上提升一个的概率
        public SkipList() {
            // TODO Auto-generated constructor stub
            random=new Random();
            clear();
        }
        /**
         *清空跳跃表
         * */
        public void clear(){
            head=new SkipListNode<T>(SkipListNode.HEAD_KEY, null);
            tail=new SkipListNode<T>(SkipListNode.TAIL_KEY, null);
            horizontalLink(head, tail);
            listLevel=0;
            nodes=0;
        }
        public boolean isEmpty(){
            return nodes==0;
        }

        public int size() {
            return nodes;
        }
        /**
         * 在最下面一层，找到要插入的位置前面的那个key
         * */
        private SkipListNode<T> findNode(int key){
            SkipListNode<T> p=head;
            while(true){
                while (p.right.key!=SkipListNode.TAIL_KEY&&p.right.key<=key) {
                    p=p.right;
                }
                if (p.down!=null) {
                    p=p.down;
                }else {
                    break;
                }

            }
            return p;
        }
        /**
         * 查找是否存储key，存在则返回该节点，否则返回null
         * */
        public SkipListNode<T> search(int key){
            SkipListNode<T> p=findNode(key);
            if (key==p.getKey()) {
                return p;
            }else {
                return null;
            }
        }
        /**
         * 向跳跃表中添加key-value
         *
         * */
        public void put(int k,T v){
            SkipListNode<T> p=findNode(k);
            //如果key值相同，替换原来的vaule即可结束
            if (k==p.getKey()) {
                p.value=v;
                return;
            }
            SkipListNode<T> q=new SkipListNode<T>(k, v);
            backLink(p, q);
            int currentLevel=0;//当前所在的层级是0
            //抛硬币
            while (random.nextDouble()<PROBABILITY) {
                //如果超出了高度，需要重新建一个顶层
                if (currentLevel>=listLevel) {
                    listLevel++;
                    SkipListNode<T> p1=new SkipListNode<T>(SkipListNode.HEAD_KEY, null);
                    SkipListNode<T> p2=new SkipListNode<T>(SkipListNode.TAIL_KEY, null);
                    horizontalLink(p1, p2);
                    vertiacallLink(p1, head);
                    vertiacallLink(p2, tail);
                    head=p1;
                    tail=p2;
                }
                //将p移动到上一层
                while (p.up==null) {
                    p=p.left;
                }
                p=p.up;

                SkipListNode<T> e=new SkipListNode<T>(k, null);//只保存key就ok
                backLink(p, e);//将e插入到p的后面
                vertiacallLink(e, q);//将e和q上下连接
                q=e;
                currentLevel++;
            }
            nodes++;//层数递增
        }
        //node1后面插入node2
        private void backLink(SkipListNode<T> node1,SkipListNode<T> node2){
            node2.left=node1;
            node2.right=node1.right;
            node1.right.left=node2;
            node1.right=node2;
        }
        /**
         * 水平双向连接
         * */
        private void horizontalLink(SkipListNode<T> node1,SkipListNode<T> node2){
            node1.right=node2;
            node2.left=node1;
        }
        /**
         * 垂直双向连接
         * */
        private void vertiacallLink(SkipListNode<T> node1,SkipListNode<T> node2){
            node1.down=node2;
            node2.up=node1;
        }
        /**
         * 打印出原始数据
         * */
        @Override
        public String toString() {
            // TODO Auto-generated method stub
            if (isEmpty()) {
                return "跳跃表为空！";
            }
            StringBuilder builder=new StringBuilder();
            SkipListNode<T> p=head;
            while (p.down!=null) {
                p=p.down;
            }

            while (p.left!=null) {
                p=p.left;
            }
            if (p.right!=null) {
                p=p.right;
            }
            while (p.right!=null) {
                builder.append(p);
                builder.append("\n");
                p=p.right;
            }

            return builder.toString();
        }

    }



    /**
     * 跳跃表的节点,包括key-value和上下左右4个指针
     * 参考：http://www.acmerblog.com/skip-list-impl-java-5773.html
     * */
    private static class SkipListNode <T>{
        public int key;
        public T value;
        public SkipListNode<T> up, down, left, right; // 上下左右 四个指针

        public static final int HEAD_KEY = Integer.MIN_VALUE; // 负无穷
        public static final int  TAIL_KEY = Integer.MAX_VALUE; // 正无穷
        public SkipListNode(int k,T v) {
            // TODO Auto-generated constructor stub
            key = k;
            value = v;
        }
        public int getKey() {
            return key;
        }
        public void setKey(int key) {
            this.key = key;
        }
        public T getValue() {
            return value;
        }
        public void setValue(T value) {
            this.value = value;
        }
        public boolean equals(Object o) {
            if (this==o) {
                return true;
            }
            if (o==null) {
                return false;
            }
            if (!(o instanceof SkipListNode<?>)) {
                return false;
            }
            SkipListNode<T> ent;
            try {
                ent = (SkipListNode<T>)  o; // 检测类型
            } catch (ClassCastException ex) {
                return false;
            }
            return (ent.getKey() == key) && (ent.getValue() == value);
        }
        @Override
        public String toString() {
            // TODO Auto-generated method stub
            return "key-value:"+key+"-"+value;
        }
    }



    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SkipList<String> list=new SkipList<String>();
        System.out.println(list);
        list.put(2, "yan");
        list.put(1, "co");
        list.put(3, "feng");
        list.put(1, "cao");//测试同一个key值
        list.put(4, "曹");
        list.put(6, "丰");
        list.put(5, "艳");
        System.out.println(list);
        System.out.println(list.size());
    }

}