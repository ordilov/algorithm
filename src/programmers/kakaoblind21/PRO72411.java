package programmers.kakaoblind21;

import java.util.*;

// [메뉴 리뉴얼] https://programmers.co.kr/learn/courses/30/lessons/72411?language=java
public class PRO72411 {

    char[][] o;
    Map<Integer, Map<String, Integer>> words;

    public String[] solution(String[] orders, int[] course) {
        words = new HashMap<>();
        o = new char[orders.length][];
        for (int i = 0; i < orders.length; i++) {
            o[i] = orders[i].toCharArray();
        }
        for (int size : course) {
            words.put(size, new HashMap<>());
            for (int i = 0; i < orders.length; i++)
                combination(0, size, i, new TreeSet<>());
        }
        return getMaxCombinations();
    }

    private String[] getMaxCombinations() {
        Set<String> fixed = new TreeSet<>();
        for (Map.Entry<Integer, Map<String, Integer>> entry : words.entrySet()) {
            int max = 0;
            List<String> temp = new ArrayList<>();
            for (Map.Entry<String, Integer> e : entry.getValue().entrySet()) {
                int value = e.getValue();
                if (value < 2 || value < max) continue;
                if (value > max) {
                    max = value;
                    temp.clear();
                }
                temp.add(e.getKey());
            }
            fixed.addAll(temp);
        }
        return fixed.toArray(new String[0]);
    }

    void combination(int start, int size, int index, Set<Character> now) {
        if (now.size() == size) {
            String n = now.toString().replaceAll("[,\\[\\]\\s]+", "");
            Map<String, Integer> sizeMap = words.get(size);
            sizeMap.put(n, sizeMap.getOrDefault(n, 0) + 1);
            return;
        }
        if (start >= o[index].length) return;
        now.add(o[index][start]);
        combination(start + 1, size, index, now);
        now.remove(o[index][start]);
        combination(start + 1, size, index, now);
    }
}
