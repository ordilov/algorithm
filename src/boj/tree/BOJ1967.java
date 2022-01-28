package boj.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[트리의 지름] https://www.acmicpc.net/problem/1967
public class BOJ1967 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Node[] tree = new Node[n + 1];
        tree[1] = new Node(0, 0, 0);
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            tree[parent].leaf = false;
            tree[child] = new Node(parent, weight, tree[parent].depth + 1);
        }
        tree[1].leaf = true;
        int max = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                Node first = tree[i];
                Node second = tree[j];
                if(!first.leaf || !second.leaf) continue;
                int firstWeight = 0;
                int secondWeight = 0;
                while (first.depth > second.depth) {
                    firstWeight += first.weight;
                    first = tree[first.parent];
                }
                while (first.depth < second.depth) {
                    secondWeight += second.weight;
                    second = tree[second.parent];
                }
                while (first != second) {
                    firstWeight += first.weight;
                    secondWeight += second.weight;
                    first = tree[first.parent];
                    second = tree[second.parent];
                }
                max = Math.max(max, firstWeight + secondWeight);
            }
        }
        System.out.println(max);
    }

    private static class Node {
        int parent;
        int weight;
        int depth;
        boolean leaf = true;

        public Node(int parent, int weight, int depth) {
            this.parent = parent;
            this.weight = weight;
            this.depth = depth;
        }
    }
}
