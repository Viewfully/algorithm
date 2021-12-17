# 算法演示项目

## 介绍
算法演示仓库，用于编写/存储基本、初级、中级、高级算法编码处理过程。
仅为作者练习、学习项目，公开学习代码
### 资料区：
####排序算法可视化演示可查看 https://visualgo.net/
#### labuladong算法小抄在线阅读：https://labuladong.gitee.io/algo/     github:  https://github.com/labuladong/fucking-algorithm


## 安装教程



## 使用说明
### 一、基础算法
#### 1.选择排序  SelectSortDemo    O(n^2)    不稳定
    执行步骤：
        1、首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置。
        2、再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
        3、重复第二步，直到所有元素均排序完毕。
    时间复杂度：
        选择排序的交换操作介于 0 和 (n - 1)次之间。选择排序的比较操作为 n (n - 1） / 2 次之间。
    选择排序的赋值操作介于 0 和 3 (n - 1） 次之间。比较次数O(n^2)，比较次数与关键字的初始状态无关，
    总的比较次数N=(n-1）+(n-2）+...+1=n*(n-1）/2。交换次数O(n），最好情况是，已经有序，交换0次；
    最坏情况交换n-1次，逆序交换n/2次。交换次数比冒泡排序少多了，由于交换所需CPU时间比比较所需的CPU时间多，
    n值较小时，选择排序比冒泡排序快。
    稳定性：
        选择排序是给每个位置选择当前元素最小的，比如给第一个位置选择最小的，在剩余元素里面给第二个元素选择第二小的，
    依次类推，直到第n-1个元素，第n个元素不用选择了，因为只剩下它一个最大的元素了。那么，在一趟选择，
    如果一个元素比当前元素小，而该小的元素又出现在一个和当前元素相等的元素后面，那么交换后稳定性就被破坏了。
    举个例子，序列5 8 5 2 9，我们知道第一遍选择第1个元素5会和2交换，那么原序列中两个5的相对前后顺序就被破坏了，
    所以选择排序是一个不稳定的排序算法。    
#### 2.冒泡排序  BubbleSortDemo    O(n^2)      稳定
    执行步骤：
        1、比较相邻的元素，如果前一个比后一个大，交换之。
        2、第一趟排序第1个和第2个一对，比较与交换，随后第2个和第3个一对比较交换，这样直到倒数第2个和最后1个，将最大的数移动到最后一位。
        3、第二趟将第二大的数移动至倒数第二位...... 因此需要n-1趟；
    时间复杂度：
        若文件的初始状态是正序的，一趟扫描即可完成排序。所需的关键字比较次数C和记录移动次数M均达到最小值：Cmin = n-1，Mmin = 0
    所以，冒泡排序最好的时间复杂度为O(n)。若初始文件是反序的，需要进行n-1趟排序。
    每趟排序要进行n-i次关键字的比较(1≤i≤n-1)，且每次比较都必须移动记录三次来达到交换记录位置。在这种情况下，比较和移动次数均达到最大值：
    Cmax = n(n-1)/2 = O(n^2)    Mmax = 3n(n-1)/2 = O(n^2)冒泡排序的最坏时间复杂度为O(n^2)。
    综上，因此冒泡排序总的平均时间复杂度为O(n^2)。
    算法稳定性：
        冒泡排序就是把小的元素往前调或者把大的元素往后调。比较是相邻的两个元素比较，交换也发生在这两个元素之间。所以，如果两个元素相等，
    是不会再交换的；如果两个相等的元素没有相邻，那么即使通过前面的两两交换把两个相邻起来，这时候也不会交换，
    所以相同元素的前后顺序并没有改变，所以冒泡排序是一种稳定排序算法。
#### 3.插入排序  InsertSortDemo    O(n^2)      稳定
    执行步骤：
        1、假设前面 n-1(其中 n>=2)个数已经是排好顺序的，现将第 n 个数插到前面已经排好的序列中，然后找到合适自己的位置，
        使得插入第n个数的这个序列也是排好顺序的。
        2、按照此法对所有元素进行插入，直到整个序列排为有序的过程，称为插入排序。
    时间复杂度：
        在插入排序中，当待排序数组是有序时，是最优的情况，只需当前数跟前一个数比较一下就可以了，这时一共需要比较N- 1次，时间复杂度为O(n)。
    最坏的情况是待排序数组是逆序的，此时需要比较次数最多，总次数记为：1+2+3+…+N-1，所以，插入排序最坏情况下的时间复杂度为O(n^2)。
    平均来说，A[1..j-1]中的一半元素小于A[j]，一半元素大于A[j]。插入排序在平均情况运行时间与最坏情况运行时间一样，是输入规模的二次函数。
    空间复杂度：插入排序的空间复杂度为常数阶O(1)
    稳定性分析：
        如果待排序的序列中存在两个或两个以上具有相同关键词的数据，排序后这些数据的相对次序保持不变，即它们的位置保持不变，
    通俗地讲，就是两个相同的数的相对顺序不会发生改变，则该算法是稳定的；如果排序后，数据的相对次序发生了变化，则该算法是不稳定的。
    关键词相同的数据元素将保持原有位置不变，所以该算法是稳定的

#### 4.归并排序  MergeSortDemo     O(N * logN)     稳定
    执行流程：
        1、申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列；
        2、设定两个指针，最初位置分别为两个已经排序序列的起始位置；
        3、比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置；
        4、重复步骤3直到某一指针达到序列尾；
        5、将另一序列剩下的所有元素直接复制到合并序列尾。
    复杂度
        归并排序比较占用内存，但却是一种效率高且稳定的算法。
    改进归并排序在归并时先判断前段序列的最大值与后段序列最小值的关系再确定是否进行复制比较。如果前段序列的最大值小于等于后段序列最小值，
    则说明序列可以直接形成一段有序序列不需要再归并，反之则需要。所以在序列本身有序的情况下时间复杂度可以降至O(n)
    TimSort可以说是归并排序的终极优化版本，主要思想就是检测序列中的天然有序子段（若检测到严格降序子段则翻转序列为升序子段）。
    在最好情况下无论升序还是降序都可以使时间复杂度降至为O(n)，具有很强的自适应性。
    	****		最好时间复杂度		最坏时间复杂度		平均时间复杂度		空间复杂度		稳定性
    传统归并排序	        O(nlogn)		O(nlogn)		O(nlogn)		T(n)			稳定
    改进归并排序      	O(n)			O(nlogn)		O(nlogn)		T(n)			稳定
    TimSort			O(n)			O(nlogn)		O(nlogn)		T(n)			稳定

    Master表达式推导
    T(N) = a*T(N/b) + O(N^d)
       1) log(b,a) > d 时间复杂度为 O(N^log(b,a))
       2) log(b,a) = d 时间复杂度为 O(N^d * logN)
       3) log(b,a) < d 时间复杂度为 O(N^d)
    归并排序可通过该表达式推出时间复杂度
    仅查看递归方法 {@link InsertSortDemo#process} 的一层即可，不需要看递归多层计算
    可得 Master 表达式为
    T(N) = 2 * T(N/2) + O(N^1)
    a = 2, b = 2,d = 1
    log(2,2) = 1
    可得出时间复杂度为 O(N * logN)

## 参与贡献


## 特技

