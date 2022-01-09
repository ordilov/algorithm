package programmers.kakaoblind18;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// [[3차] 압축] https://programmers.co.kr/learn/courses/30/lessons/17684?language=java#
public class PRO17684 {

    public int[] solution(String msg) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            map.put(String.valueOf((char) ('A' + i)), i + 1);
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < msg.length(); i++) {
            int index = i + 1;
            while (index <= msg.length() && map.get(msg.substring(i, index)) != null) {
                index++;
            }
            String existWord = msg.substring(i, index - 1);
            list.add(map.get(existWord));
            if (index < msg.length()) {
                map.put(msg.substring(i, index), map.size() + 1);
            }
            if (existWord.length() > 1) i += existWord.length() - 1;
        }

        return list.stream().mapToInt(i -> i).toArray();
    }
}
