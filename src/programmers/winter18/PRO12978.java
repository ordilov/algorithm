package programmers.winter18;

import java.util.*;

// [배달] https://programmers.co.kr/learn/courses/30/lessons/12978?language=java
public class PRO12978 {

    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        int MAX = 500001;

        int[][] d = new int[N+1][N+1];
        for(int[] distance: d){
            Arrays.fill(distance, MAX);
        }
        for(int i = 1; i <= N; i++){
            d[i][i] = 0;
        }

        for (int[] ints : road) {
            int from = ints[0];
            int to = ints[1];
            int cost = ints[2];
            d[to][from] = d[from][to] = Math.min(d[from][to], cost);
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                }
            }
        }


        for(int i = 1; i <= N; i++){
            if(d[1][i] <= K) answer++;
        }

        return answer;
    }
}
