# 算法演示项目

#### 介绍
算法演示仓库，用于编写/存储基本、初级、中级、高级算法编码处理过程。
仅为作者练习、学习项目，公开学习代码
排序算法可视化演示可查看
https://visualgo.net/


#### 安装教程



#### 使用说明
##### 1.选择排序  SelectSortDemo
##### 2.冒泡排序  BubbleSortDemo
##### 3.插入排序  InsertSortDemo
    Master表达式推导
    T(N) = a*T(N/b) + O(N^d)
       1) log(b,a) > d 时间复杂度为 O(N^log(b,a))
       2) log(b,a) = d 时间复杂度为 O(N^d * logN)
       3) log(b,a) < d 时间复杂度为 O(N^d)
    归并排序可通过该表达式推出时间复杂度
    仅查看递归方法 {@link #process} 的一层即可，不需要看递归多层计算
    可得 Master 表达式为
    T(N) = 2 * T(N/2) + O(N^1)
    a = 2, b = 2,d = 1
    log(2,2) = 1
    可得出时间复杂度为 O(N * logN)

#### 参与贡献


#### 特技

