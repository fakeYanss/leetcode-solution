## leetcode solution

> 最近苦于记忆力减退，注意力不集中，常常自我怀疑是否开始步入中年。

这是一个新开的LeetCode算法题项目， 主旨在于锻炼思维能力，捡起大学的知识，并保证持久的职场竞争力。

**20210317 updated:** 被迫刷题:smile:

[LeetCode Algorithm Table](leetcode-algorithm-table.md)

**项目期望：**
- [ ] 用java解决包含但不限于array/tree/graph等几种数据结构、分治/DP/backtrack等几种算法思想的题目，并取得平均题解runtime beats 80%的成绩。
- [ ] ~~每天打卡~~（估计很快失败）平均每天1题保本，2题赚翻。



**项目构建：**

根目录下有内嵌gradlew构建脚本，可以直接执行。
```shell script
git clone git@github.com:fakeYanss/leetcode-solution.git
cd leetcode
# on linux & mac
./gradlew
# on windows
gradlew.bat
```

**项目结构:**

内层结构按题目类型划分，并不十分严谨，可能按数据结构分类和按算法分类会有部分交叉，不过大体统一。
```text
├─gradle
│  └─wrapper
├─gradlew
├─gradlew.bat 
├─build.gradle
└─src
    └─main
        └─java
            └─me
                └─foreti
                    └─leetcode
                        ├─array
                        │  ├─_11_container_with_most_water
                        │  ├─_15_3sum
                        │  ├─_16_3sum_closest
                        │  ├─_18_4sum
                        ├─tree
                        ├─graph
                        └─util
```
