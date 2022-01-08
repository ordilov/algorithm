package programmers.kakaoblind18;

// [[3차] n진수 게임] https://programmers.co.kr/learn/courses/30/lessons/17687?language=java
public class PRO17687 {

    public String solution(int n, int t, int m, int p) {
        int start = 0;
        int tOrder = 0;
        int order = 1;

        String answer = "";
        while (tOrder < t) {
            for (char num : Integer.toString(start, n).toUpperCase().toCharArray()) {
                if (order == p) {
                    tOrder++;
                    answer += num;
                    if (tOrder == t) break;
                }
                order = (order % m) + 1;
            }
            start++;
        }
        return answer;
    }

}
