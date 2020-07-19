import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Map.Entry;

/*
 * @lc app=leetcode.cn id=692 lang=java
 *
 * [692] 前K个高频单词
 *
 * https://leetcode-cn.com/problems/top-k-frequent-words/description/
 *
 * algorithms
 * Medium (37.82%)
 * Total Accepted:    1.3K
 * Total Submissions: 3.4K
 * Testcase Example:  '["i", "love", "leetcode", "i", "love", "coding"]\n2'
 *
 * 给一非空的单词列表，返回前 k 个出现次数最多的单词。
 * 
 * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
 * 
 * 示例 1：
 * 
 * 
 * 输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * 输出: ["i", "love"]
 * 解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
 * ⁠   注意，按字母顺序 "i" 在 "love" 之前。
 * 
 * 
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"],
 * k = 4
 * 输出: ["the", "is", "sunny", "day"]
 * 解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
 * ⁠   出现次数依次为 4, 3, 2 和 1 次。
 * 
 * 
 * 
 * 
 * 注意：
 * 
 * 
 * 假定 k 总为有效值， 1 ≤ k ≤ 集合元素数。
 * 输入的单词均由小写字母组成。
 * 
 * 
 * 
 * 
 * 扩展练习：
 * 
 * 
 * 尝试以 O(n log k) 时间复杂度和 O(n) 空间复杂度解决。
 * 
 * 
 */
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> dic = new HashMap<String, Integer>();
        for (String word : words) {
            if (dic.containsKey(word)) {
                dic.put(word, dic.get(word)+1);
            }else {
                dic.put(word, 1);
            }
        }
        PriorityQueue<Entry> minHeap = new PriorityQueue<Entry>(new Comparator<Entry>() {
            @Override
            public int compare(Entry o1, Entry o2) {
                return o1.getValue() == o2.getValue() ? 0 : o1.getValue() > o2.getValue() ? -1 : 1;
            }
        });
        for (Entry entry : dic.entrySet()) {
            minHeap.add(entry);
            if (minHeap.size() > k + 1) {
                minHeap.poll();
            }
        }
        if (minHeap.size() > k) minHeap.poll();
        List<String> ans = new ArrayList<>();
        Iterator<Entry> a = minHeap.iterator();
        while (a.hasNext()) {
            Entry e = a.next();
            ans.add((String)e.getKey());
        }
        return ans;
    }
}

