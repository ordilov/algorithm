package programmers.kakaoblind21;

import java.util.*;

// [순위 검색] https://programmers.co.kr/learn/courses/30/lessons/72412?language=java
public class PRO72412 {

    public int[] solution(String[] info, String[] query) {
        Map<String, List<Integer>> map = new HashMap<>();
        for (String i : info) {
            String[] split = i.split(" ");
            int score = Integer.parseInt(split[4]);
            for (String c : combination(split)) {
                List<Integer> scores = map.getOrDefault(c, new ArrayList<>());
                scores.add(score);
                map.put(c, scores);
            }
        }

        map.values().forEach(Collections::sort);

        int[] answer = new int[query.length];
        for (int j = 0; j < query.length; j++) {
            String[] split = query[j].split(" ");
            int score = Integer.parseInt(split[7]);
            String q = split[0] + split[2] + split[4] + split[6];
            if (map.get(q) == null) continue;
            answer[j] = binarySearch(map.get(q), score);
        }
        return answer;
    }

    private String[] combination(String[] split) {
        String[] comb = new String[16];
        Arrays.fill(comb, "");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < (2 << i); j++) {
                int size = 8 >> i;
                for (int k = 0; k < size; k++) {
                    comb[j * size + k] += j % 2 == 0 ? '-' : split[i];
                }
            }
        }
        return comb;
    }

    private int binarySearch(List<Integer> a, int score) {
        int low = 0;
        int high = a.size();
        int mid;
        while (low < high) {
            mid = (low + high) / 2;
            if (a.get(mid) >= score) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return a.size() - low;
    }

}