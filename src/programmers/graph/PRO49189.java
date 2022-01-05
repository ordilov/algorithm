package programmers.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

// [가장 먼 노드] https://programmers.co.kr/learn/courses/30/lessons/49189?language=java
public class PRO49189 {

    boolean[] visited;
    int[] distances;

    public int solution(int n, int[][] edge) {
        int answer = 0;
        List<List<Integer>> graph = new ArrayList<>();
        visited = new boolean[n+1];
        distances = new int[n+1];
        Arrays.fill(distances, 20001);
        distances[1] = 0;
        visited[1] = true;

        for(int i = 0; i <= n; i++){
            graph.add(new ArrayList<>());
        }
        for(int[] e : edge){
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(1, 0));
        int max = 0;

        while(!queue.isEmpty()){
            Node now = queue.poll();
            int dist = now.dist;
            int to = now.to;
            distances[to] = Math.min(distances[to], dist + 1);
            if(distances[to] > max){
                max = distances[to];
                answer = 1;
            } else if(distances[to] == max){
                answer++;
            }
            for(int i : graph.get(to)){
                if(visited[i]) continue;
                visited[i] = true;
                queue.add(new Node(i, dist + 1));
            }
        }

        return answer;
    }

    static class Node implements Comparable<Node>{
        int to;
        int dist;
        public Node(int to, int dist){
            this.to = to;
            this.dist = dist;
        }
        @Override
        public int compareTo(Node n){
            return this.dist - n.dist;
        }
    }
}
