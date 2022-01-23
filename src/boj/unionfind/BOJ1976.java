package boj.unionfind;

import java.io.*;
import java.util.StringTokenizer;

//[여행 가자] https://www.acmicpc.net/problem/1976
public class BOJ1976 {

    private static int[] parents;
    private static int[] ranks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        parents = new int[n+1];
        for(int i = 1; i < n+1; i++)
            parents[i] = i;
        ranks = new int[n+1];
        for(int i = 1; i <= n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++){
                if(Integer.parseInt(st.nextToken()) == 0) continue;
                union(i, j);
            }
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int parent = find(Integer.parseInt(st.nextToken()));
        boolean movable = true;
        for(int i = 0; i < m - 1; i++){
            int path = Integer.parseInt(st.nextToken());
            if(find(path) != parent) {
                movable = false;
                break;
            }
        }
        bw.write(movable ? "YES" : "NO");
        bw.flush();
        bw.close();
    }

    private static void union(int a, int b){
        int pa = find(a);
        int pb = find(b);
        if(pa == pb) return;
        if(ranks[pa] < ranks[pb]){
            parents[pa] = pb;
        }else if(ranks[pa] > ranks[pb]){
            parents[pb] = pa;
        }else{
            parents[pa] = pb;
            ranks[pb]++;
        }
    }

    private static int find(int a){
        if(parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }
}
