package programmers.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// [단속카메라] https://programmers.co.kr/learn/courses/30/lessons/42884?language=java
public class PRO42884 {

    public int solution(int[][] routes) {
        /*
        나가는 시간이 빠른 기준으로 하면 될까?
        */
        List<Move> moves = new ArrayList<>();
        for(int[] r : routes){
            moves.add(new Move(r[0], r[1]));
        }
        Collections.sort(moves);
        int answer = 1;
        int now = moves.get(0).end;
        for(Move m : moves){
            if(m.start <= now) continue;
            now = m.end;
            answer++;
        }
        return answer;
    }
    static class Move implements Comparable<Move>{
        int start;
        int end;
        public Move(int start, int end){
            this.start = start;
            this.end = end;
        }
        @Override
        public int compareTo(Move m){
            return this.end - m.end;
        }
    }
}
