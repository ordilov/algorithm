package programmers.montly3;

// [n^2 배열 자르기] https://programmers.co.kr/learn/courses/30/lessons/87390?language=java
public class PRO87390 {

    public int[] solution(int n, long left, long right) {
        int distance = (int)(right - left);
        int[] answer = new int[distance + 1];
        for(int i = 0; i <= distance; i++){
            long start = i + left;
            answer[i] = (int)(start % n + 1);
            if(answer[i] < (int)(start / n + 1))
                answer[i] = (int)(start / n + 1);
        }
        return answer;
    }
}
