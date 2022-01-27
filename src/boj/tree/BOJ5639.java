package boj.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//[이진 검색 트리] https://www.acmicpc.net/problem/5639
public class BOJ5639 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        Node root = new Node(Integer.parseInt(br.readLine()));

        while ((str = br.readLine()) != null && !str.equals("")) {
            root.insert(Integer.parseInt(str));
        }
        Deque<Node> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        q.add(root);
        while(!q.isEmpty()) {
            Node node = q.removeFirst();
            if(visited.contains(node.data)){
                System.out.println(node.data);
                continue;
            }
            visited.add(node.data);
            q.addFirst(node);
            if(node.right != null) q.addFirst(node.right);
            if(node.left != null) q.addFirst(node.left);
        }
    }

    private static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }

        void insert(int n) {
            if (n < this.data) {
                if (this.left == null)
                    this.left = new Node(n);
                else this.left.insert(n);
            } else {
                if (this.right == null)
                    this.right = new Node(n);
                else this.right.insert(n);
            }
        }
    }

}
