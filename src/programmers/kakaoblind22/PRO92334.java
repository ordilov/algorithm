package programmers.kakaoblind22;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// [신고 결과 받기] https://programmers.co.kr/learn/courses/30/lessons/92334?language=java
public class PRO92334 {

    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        Map<String, Set<String>> reporters = new HashMap<>();
        Map<String, Integer> idMap = new HashMap<>();

        for (int i = 0; i < id_list.length; i++) {
            reporters.put(id_list[i], new HashSet<>());
            idMap.put(id_list[i], i);
        }

        for (String s : report) {
            String[] arr = s.split(" ");
            reporters.get(arr[1]).add(arr[0]);
        }

        for(Map.Entry<String, Set<String>> entry : reporters.entrySet()){
            if(entry.getValue().size() >= k){
                for(String s : entry.getValue()){
                    answer[idMap.get(s)]++;
                }
            }
        }

        return answer;
    }
}
