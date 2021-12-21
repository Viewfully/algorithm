package com.other.bloom;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;


/**
 * @ClassName: BloomFilterDemo
 * @Author: Jack.Zhang
 * @Description: 布隆过滤器，默认误判率为0.03，可以手动设置误判率，但是需要的空间更多
 * TODO 参考 https://zhuanlan.zhihu.com/p/94433082
 * 注意需要使用lib目录中的guava-28.0-jre.jar包，使用前右键该jar -> Add as library将该jar包进行加载
 * @Date: 2021-12-17
 */
public class BloomFilterDemo {


    /**
     * 常见的布隆过滤器应用场景：
     * 1、网页爬虫对 URL 去重，避免爬取相同的 URL 地址；
     * 2、反垃圾邮件，从数十亿个垃圾邮件列表中判断某邮箱是否垃圾邮箱；
     * 3、Google Chrome 使用布隆过滤器识别恶意 URL；
     * 4、Medium 使用布隆过滤器避免推荐给用户已经读过的文章；
     * 5、Google BigTable，Apache HBbase 和 Apache Cassandra
     *  使用布隆过滤器减少对不存在的行和列的查找。 除了上述的应用场景之外，
     *  布隆过滤器还有一个应用场景就是解决缓存穿透的问题。
     *  所谓的缓存穿透就是服务调用方每次都是查询不在缓存中的数据，
     *  这样每次服务调用都会到数据库中进行查询，如果这类请求比较多的话，
     *  就会导致数据库压力增大，这样缓存就失去了意义。
     * */
    public static void main(String[] args) {
        int total = 10000000; // 总数量
        BloomFilter<CharSequence> bf =
          BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), total,0.0002);
        // 这里可以手动设置误判率，注意：追求误判率越低，则需要占用更多的内存空间（即，bit数组大小）
        // 初始化 10000000 条数据到过滤器中
        for (int i = 0; i < total; i++) {
            bf.put("" + i);
        }
        // 判断值是否存在过滤器中
        int count = 0;
        for (int i = 0; i < total + 100000; i++) {
            if (bf.mightContain("" + i)) {
                count++;
            }
        }
        System.out.println("已匹配数量 " + count);
    }
}