package programmers.kakaoblind22;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

// [주차 요금 계산] https://programmers.co.kr/learn/courses/30/lessons/92341?language=java
public class PRO92341 {

    public int[] solution(int[] fees, String[] records) {

        final double B_MINUTE = fees[0];
        final int B_COST = fees[1];
        final int U_MINUTE = fees[2];
        final int U_COST = fees[3];
        final int END_OF_DAY = 1439;

        Map<String, Integer> last = new HashMap<>();
        Map<String, Integer> minutes = new TreeMap<>();

        for (String record : records) {
            String[] split = record.split(" ");
            String[] time = split[0].split(":");
            int minute = Integer.parseInt(time[1]) + Integer.parseInt(time[0]) * 60;
            String car = split[1];
            String dir = split[2];
            if (dir.equals("IN")) last.put(car, minute);
            else {
                minutes.put(car, minutes.getOrDefault(car, 0) + minute - last.get(car));
                last.remove(car);
            }
        }

        for (Map.Entry<String, Integer> m : last.entrySet()) {
            int remains = END_OF_DAY - m.getValue();
            minutes.put(m.getKey(), minutes.getOrDefault(m.getKey(), 0) + remains);
        }

        int[] answer = new int[minutes.size()];
        int i = 0;
        for (Map.Entry<String, Integer> m : minutes.entrySet()) {
            int cost = B_COST;
            if (m.getValue() > B_MINUTE)
                cost += (int) (Math.ceil((m.getValue() - B_MINUTE) / U_MINUTE) * U_COST);
            answer[i++] = cost;
        }
        return answer;
    }
}
