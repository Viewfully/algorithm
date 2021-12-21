# TMD算法

## 介绍
TMD算法仓库，用于编写/存储基本、初级、中级、高级、终级算法编码处理过程。
仅为作者练习、学习项目，公开学习代码
### 资料区：
#### 排序算法可视化演示可查看 https://visualgo.net/
#### labuladong算法：
    算法小抄在线阅读： https://labuladong.gitee.io/algo/     
    github:  https://github.com/labuladong/fucking-algorithm


## 安装教程
调试运行


## 使用说明
### 〇、基础数据结构（初学者请看这里）

### 一、复杂一点的数据结构
#### 1.平衡二叉树
平衡二叉树是基于二分法的策略提高数据的查找速度的二叉树的数据结构；
##### 特点：
平衡二叉树是采用二分法思维把数据按规则组装成一个树形结构的数据，用这个树形结构的数据减少无关数据的检索，大大的提升了数据检索的速度；
平衡二叉树的数据结构组装过程有以下规则：

    （1）非叶子节点只能允许最多两个子节点存在。
    （2）每一个非叶子节点数据分布规则为左边的子节点小当前节点的值，右边的子节点大于当前节点的值(这里值是基于自己的算法规则而定的，比如hash值)；
    
![Alt text](https://pic1.zhimg.com/80/v2-28e39093993f673de576f57ea614d604_720w.jpg "示例")

平衡树的层级结构：因为平衡二叉树查询性能和树的层级（h高度）成反比，h值越小查询越快、为了保证树的结构左右两端数据大致平衡降低二叉树的查询难度一般
会采用一种算法机制实现节点数据结构的平衡，实现了这种算法的有比如TreeMap、红黑树，使用平衡二叉树能保证数据的左右两边的节点层级相差不会大于1,
通过这样避免树形结构由于删除增加变成线性链表影响查询效率，保证数据平衡的情况下查找数据的速度近于二分法查找；

![Alt text](https://pic4.zhimg.com/80/v2-2b52d4e523f374f41b5429cd587443db_720w.jpg "退化为链表")

    总结平衡二叉树特点：
    （1）非叶子节点最多拥有两个子节点；
    （2）非叶子节值大于左边子节点、小于右边子节点；
    （3）树的左右两边的层级数相差不会大于1;
    （4）没有值相等重复的节点;

#### 2.B树（又称为B-树，B-Tree）
B树和平衡二叉树稍有不同的是B树属于多叉树又名平衡多路查找树（查找路径不只两个），数据库索引技术里大量使用者B树和B+树的数据结构;

##### 规则：
    （1）排序方式：所有节点关键字是按递增次序排列，并遵循左小右大原则；
    （2）子节点数：非叶节点的子节点数>1，且<=M ，且M>=2，空树除外（注：M阶代表一个树节点最多有多少个查找路径，M=M路,当M=2则是2叉树,M=3则是3叉）；
    （3）关键字数：枝节点的关键字数量大于等于ceil(m/2)-1个且小于等于M-1个（注：ceil()是个朝正无穷方向取整的函数 如ceil(1.1)结果为2);
    （4）特别注意这一点：所有叶子节点均在同一层、叶子节点除了包含了关键字和关键字记录的指针外也有指向其子节点的指针只不过其指针地址都为null对应下图最后一层节点的空格子;
最后我们用一个图和一个实际的例子来理解B树（这里为了理解方便我就直接用实际字母的大小来排列C>B>A）

![Alt text](https://pic2.zhimg.com/80/v2-2c2264cc1c6c603dfeca4f84a2575901_720w.jpg "B树")

B树的查询流程：

    如上图我要从上图中找到E字母，查找流程如下
    （1）获取根节点的关键字进行比较，当前根节点关键字为M，E<M（26个字母顺序），所以往找到指向左边的子节点（二分法规则，左小右大，左边放小于当前节点值的子节点、右边放大于当前节点值的子节点）；
    （2）拿到关键字D和G，D<E<G 所以直接找到D和G中间的节点；
    （3）拿到E和F，因为E=E 所以直接返回关键字和指针信息（如果树结构里面没有包含所要查找的节点则返回null）；

B树的插入节点流程

    定义一个5阶树（平衡5路查找树），现在我们要把3、8、31、11、23、29、50、28 这些数字构建出一个5阶树出来;
    遵循规则：
    （1）节点拆分规则：当前是要组成一个5路查找树，那么此时m=5,关键字数必须<=5-1（这里关键字数>4就要进行节点拆分）；
    （2）排序规则：满足节点本身比左边节点大，比右边节点小的排序规则;
    
先插入 3、8、31、11    
![Alt text](https://pic4.zhimg.com/80/v2-e1d65c9c6236d4768c89e8e103e12583_720w.jpg "插入流程")

再插入23、29
![Alt text](https://pic1.zhimg.com/80/v2-66cdb6187cbc5227fd8c4aabe7282e6c_720w.jpg "插入流程")

再插入50、28
![Alt text](https://pic1.zhimg.com/80/v2-3057eaab2b1764dd51c2a8658791cc98_720w.jpg "插入流程")
B树节点的删除

##### 规则：
    （1）节点合并规则：当前是要组成一个5路查找树，那么此时m=5,关键字数必须大于等于ceil（5/2）（这里关键字数<2就要进行节点合并）；
    （2）满足节点本身比左边节点大，比右边节点小的排序规则;
    （3）关键字数小于二时先从子节点取，子节点没有符合条件时就向向父节点取，取中间值往父节点放；
    
![Alt text](https://pic2.zhimg.com/80/v2-a0f981fc847772cb28869927cd4fe66d_720w.jpg "删除流程")

##### 特点：
B树相对于平衡二叉树的不同是，每个节点包含的关键字增多了，特别是在B树应用到数据库中的时候，数据库充分利用了磁盘块的原理（磁盘数据存储是采用块的形式存储的，
每个块的大小为4K，每次IO进行数据读取时，同一个磁盘块的数据可以一次性读取出来）把节点大小限制和充分使用在磁盘快大小范围；
把树的节点关键字增多后树的层级比原来的二叉树少了，减少数据查找的次数和复杂度;

#### 3.B+树
B+树是B树的一个升级版，相对于B树来说B+树更充分的利用了节点的空间，让查询速度更加稳定，其速度完全接近于二分法查找。为什么说B+树查找的效率要比B树更高、更稳定；

##### 规则
    （1）B+跟B树不同B+树的非叶子节点不保存关键字记录的指针，只进行数据索引，这样使得B+树每个非叶子节点所能保存的关键字大大增加；
    （2）B+树叶子节点保存了父节点的所有关键字记录的指针，所有数据地址必须要到叶子节点才能获取到。所以每次数据查询的次数都一样；
    （3）B+树叶子节点的关键字从小到大有序排列，左边结尾数据都会保存右边节点开始数据的指针。
    （4）非叶子节点的子节点数=关键字数（来源百度百科）（根据各种资料 这里有两种算法的实现方式，另一种为非叶节点的关键字数=子节点数-1（来源维基百科)，虽然他们数据排列结构不一样，但其原理还是一样的Mysql 的B+树是用第一种方式实现）;

![Alt text](https://pic4.zhimg.com/80/v2-5f069fd820637db1b877fdd6799a2b67_720w.jpg "B+树结构")

（百度百科算法结构示意图）
![Alt text](https://pic2.zhimg.com/80/v2-9644d1a1f83d3e45da779f2e63c35d55_720w.jpg "B+树结构")

##### 特点
    1、B+树的层级更少：相较于B树B+每个非叶子节点存储的关键字数更多，树的层级更少所以查询数据更快；
    2、B+树查询速度更稳定：B+所有关键字数据地址都存在叶子节点上，所以每次查找的次数都相同所以查询速度要比B树更稳定;
    3、B+树天然具备排序功能：B+树所有的叶子节点数据构成了一个有序链表，在查询大小区间的数据时候更方便，数据紧密性很高，缓存的命中率也会比B树高。
    4、B+树全节点遍历更快：B+树遍历整棵树只需要遍历所有的叶子节点即可，，而不需要像B树一样需要对每一层进行遍历，这有利于数据库做全表扫描。
B树相对于B+树的优点是，如果经常访问的数据离根节点很近，而B树的非叶子节点本身存有关键字其数据的地址，所以这种数据检索的时候会要比B+树快。

#### 4.B*树
##### 规则
    B*树是B+树的变种，相对于B+树他们的不同之处如下：
    （1）首先是关键字个数限制问题，B+树初始化的关键字初始化个数是cei(m/2)，b*树的初始化个数为（cei(2/3*m)）
    （2）B+树节点满时就会分裂，而B*树节点满时会检查兄弟节点是否满（因为每个节点都有指向兄弟的指针），如果兄弟节点未满则向兄弟节点转移关键字，如果兄弟节点已满，则从当前节点和兄弟节点各拿出1/3的数据创建一个新的节点出来；
    
##### 特点
在B+树的基础上因其初始化的容量变大，使得节点空间使用率更高，而又存有兄弟节点的指针，可以向兄弟节点转移关键字的特性使得B*树额分解次数变得更少；

![Alt text](https://pic3.zhimg.com/80/v2-e8bf8ee3230f3d39d59ce5e76a2ee32e_720w.jpg "B*树结构")



  



### 二、基础算法（包com.basic）
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
    


### 三、初级算法
### 四、中级算法
### 五、高级算法
### 六、终级算法
### 七、其他算法（包com.other）
#### 1.布隆过滤器  BloomFilterDemo
##### 1.1布隆过滤器简介
当你往简单数组或列表中插入新数据时，将不会根据插入项的值来确定该插入项的索引值。
这意味着新插入项的索引值与数据值之间没有直接关系。这样的话，
当你需要在数组或列表中搜索相应值的时候，你必须遍历已有的集合。
若集合中存在大量的数据，就会影响数据查找的效率。
针对这个问题，你可以考虑使用哈希表。利用哈希表你可以通过对 “值” 
进行哈希处理来获得该值对应的键或索引值，然后把该值存放到列表中对应的索引位置。
这意味着索引值是由插入项的值所确定的，当你需要判断列表中是否存在该值时，
只需要对值进行哈希处理并在相应的索引位置进行搜索即可，这时的搜索速度是非常快的。
![Alt text](https://pic3.zhimg.com/80/v2-ba324df953f121b077f7bdc2a6109f0a_720w.jpg "实现流程")
根据定义，布隆过滤器可以检查值是 “可能在集合中” 还是 “绝对不在集合中”。“可能” 表示有一定的概率，也就是说可能存在一定为误判率。那为什么会存在误判呢？
下面我们来分析一下具体的原因。布隆过滤器（Bloom Filter）本质上是由长度为 m 的位向量或位列表（仅包含 0 或 1 位值的列表）组成，
最初所有的值均设置为 0，如下图所示。
![Alt text](https://pic2.zhimg.com/80/v2-3590d269c6cd9d5be764b4bc79335da5_720w.jpg "位图")
为了将数据项添加到布隆过滤器中，我们会提供 K 个不同的哈希函数，并将结果位置上对应位的值置为 “1”。在前面所提到的哈希表中，
我们使用的是单个哈希函数，因此只能输出单个索引值。而对于布隆过滤器来说，我们将使用多个哈希函数，这将会产生多个索引值。
![Alt text](https://pic4.zhimg.com/80/v2-8c28b1d5990396202a05430bde51511b_720w.jpg "位图")
如上图所示，当输入 “semlinker” 时，预设的 3 个哈希函数将输出 2、4、6，我们把相应位置 1。假设另一个输入 ”kakuqo“，哈希函数输出 3、4 和 7。
你可能已经注意到，索引位 4 已经被先前的 “semlinker” 标记了。
此时，我们已经使用 “semlinker” 和 ”kakuqo“ 两个输入值，填充了位向量。当前位向量的标记状态为：
![Alt text](https://pic2.zhimg.com/80/v2-9cfe294a29af4209e476fccfae466d7d_720w.jpg "位图")
当对值进行搜索时，与哈希表类似，我们将使用 3 个哈希函数对 ”搜索的值“ 进行哈希运算，并查看其生成的索引值。假设，当我们搜索 ”fullstack“ 时，
3 个哈希函数输出的 3 个索引值分别是 2、3 和 7：
![Alt text](https://pic2.zhimg.com/80/v2-9a3dec489430cffd38b310c33242bf51_720w.jpg "位图")
从上图可以看出，相应的索引位都被置为 1，这意味着我们可以说 ”fullstack“ 可能已经插入到集合中。事实上这是误报的情形，
产生的原因是由于哈希碰撞导致的巧合而将不同的元素存储在相同的比特位上。幸运的是，布隆过滤器有一个可预测的误判率（FPP）：
![Alt text](https://pic4.zhimg.com/80/v2-af6d3aff3d1e50759226610d2c469b2b_720w.jpg "计算公式")
###### n 是已经添加元素的数量；
###### k 哈希的次数；
###### m 布隆过滤器的长度（如比特数组的大小）
极端情况下，当布隆过滤器没有空闲空间时（满），每一次查询都会返回 true 。这也就意味着 m 的选择取决于期望预计添加元素的数量 n ，并且 m 需要远远大于 n 。
实际情况中，布隆过滤器的长度 m 可以根据给定的误判率（FFP）的和期望添加的元素个数 n 的通过如下公式计算：
![Alt text](https://pic3.zhimg.com/80/v2-19ddb2632e68e2666fd09e3c5441f542_720w.jpg "计算公式")
了解完上述的内容之后，我们可以得出一个结论，当我们搜索一个值的时候，若该值经过 K 个哈希函数运算后的任何一个索引位为 ”0“，那么该值肯定不在集合中。
但如果所有哈希索引值均为 ”1“，则只能说该搜索的值可能存在集合中。
##### 1.2布隆过滤器应用
    ①在实际工作中，布隆过滤器常见的应用场景如下：
    ②网页爬虫对 URL 去重，避免爬取相同的 URL 地址；
    ③反垃圾邮件，从数十亿个垃圾邮件列表中判断某邮箱是否垃圾邮箱；
    ④Google Chrome 使用布隆过滤器识别恶意 URL；
    ⑤Medium 使用布隆过滤器避免推荐给用户已经读过的文章；
    ⑥Google BigTable，Apache HBbase 和 Apache Cassandra 使用布隆过滤器减少对不存在的行和列的查找。 
    除了上述的应用场景之外，布隆过滤器还有一个应用场景就是解决缓存穿透的问题。所谓的缓存穿透就是服务调用方每次都是查询不在缓存中的数据，
    这样每次服务调用都会到数据库中进行查询，如果这类请求比较多的话，就会导致数据库压力增大，这样缓存就失去了意义。







## 参与贡献


## 特技

