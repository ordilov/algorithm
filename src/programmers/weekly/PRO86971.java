package programmers.weekly;

// [전력망을 둘로 나누기] https://programmers.co.kr/learn/courses/30/lessons/86971?language=java
public class PRO86971 {

    public int solution(int n, int[][] wires) {
        /*
        트리 자료구조를 2개로 분할하는 문제
         */
        int answer = 100;
        for(int i = 0; i < n-1; i++){
            DisjointSet set = new DisjointSet(n);
            for(int j = 0; j < n-1; j++){
                if(j == i) continue;
                set.merge(wires[j][0], wires[j][1]);
            }
            int partialSize = set.getPartialSize();
            answer = Math.min(answer, Math.abs(n - 2 * partialSize));
        }
        return answer;
    }

    static class DisjointSet{
        int[] parents;
        int[] rank;

        public DisjointSet(int n){
            parents = new int[n+1];
            rank = new int[n+1];
            for(int i = 1; i < n+1; i++){
                parents[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int u){
            if(parents[u] == u) return u;
            return parents[u] = find(parents[u]);
        }

        public void merge(int u, int v){
            u = find(u);
            v = find(v);
            if(u == v) return;
            parents[u] = v;
        }

        private int getPartialSize(){
            int size = 0;
            int partialParent = find(1);
            for(int i = 1; i < parents.length; i++){
                if(find(i) == partialParent)
                    size++;
            }
            return size;
        }
    }
}
