package com.chaney.infra.graalpy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopKTest {

    public static void main(String[] args) {
        String[] arr = new String[]{"ab", "ab", "b", "b", "b", "c", "c", "c"};
        int k = 3;
        List<String> toppedK = topK(arr, k);
        System.out.println(toppedK);
    }

    /**
     * topk,对于出现频率相同的，只返回一个
     */
    public static List<String> topK(String[] arr, int k) throws IllegalArgumentException {
        Map<String, Integer> cntMap = new HashMap<>();
        if (k < 0 || arr == null) {
            throw new IllegalArgumentException("k should be positive integer and arr should not be null");
        }
        if (k == 0 || arr.length == 0) {
            return List.of();
        }
        for (String s : arr) {
            cntMap.compute(s, (_, val) -> val == null ? 0 : val + 1);
        }
        // 如果预期的数量较大，不跳过，否则跳过 cntMap.size() - k
        int skipCnt = Math.max(0, cntMap.size() - k);
        return cntMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .skip(skipCnt)
                .map(Map.Entry::getKey)
                .limit(k)
                .toList();
    }
}
