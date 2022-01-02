package programmers.weekly;

// [피로도] https://programmers.co.kr/learn/courses/30/lessons/87946?language=java
public class PRO87946 {

    public static boolean[] visited;

    public int solution(int k, int[][] dungeons) {
        /*
        최소 필요 피로도가 제일 작은 순으로 돈다면? X
        소모 피로도가 제일 작은 순으로 돈다면? X
        dungeons 최대가 8이므로 완전 탐색
        */

        visited = new boolean[dungeons.length];
        return enter(k, 0, dungeons);
    }

    public int enter(int remains, int count, int[][] dungeons){
        int answer = count;
        for(int i = 0; i < dungeons.length; i++){
            if(visited[i] || dungeons[i][0] > remains) continue;
            visited[i] = true;
            answer = Math.max(answer, enter(remains - dungeons[i][1], count+1, dungeons));
            visited[i] = false;
        }
        return answer;
    }

}
