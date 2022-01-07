package programmers.winter18;

import java.util.HashSet;
import java.util.Set;

// [영어 끝말잇기] https://programmers.co.kr/learn/courses/30/lessons/12981?language=java
public class PRO12981 {

    public int[] solution(int n, String[] words) {
        Set<String> done = new HashSet<>();
        done.add(words[0]);
        int order;
        for (order = 1; order < words.length; order++) {
            char first = words[order].charAt(0);
            char end = words[order - 1].charAt(words[order - 1].length() - 1);
            if (first != end) break;
            if (done.contains(words[order])) break;
            done.add(words[order]);
        }
        int person = order % n + 1;
        int sequence = order / n + 1;
        if (order == words.length) person = sequence = 0;
        return new int[]{person, sequence};
    }

}
