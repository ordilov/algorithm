package boj.tree;

import java.io.*;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

//[트리 순회] https://www.acmicpc.net/problem/1991
public class BOJ1991 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(bufferedReader.readLine());
        Map<Character, Node> tree = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char[] s = bufferedReader.readLine().replace(" ", "").toCharArray();
            tree.put(s[0], new Node(s[0], s[1], s[2]));
        }
        sb.append(traverse(n, tree, new Order[]{Order.RIGHT, Order.LEFT, Order.NOW}));
        sb.append(traverse(n, tree, new Order[]{Order.RIGHT, Order.NOW, Order.LEFT}));
        sb.append(traverse(n, tree, new Order[]{Order.NOW, Order.RIGHT, Order.LEFT}));

        bufferedWriter.write(sb.toString());
        bufferedWriter.flush();
        bufferedWriter.close();
        bufferedReader.close();
    }

    private static String traverse(int n, Map<Character, Node> tree, Order[] orders) {
        StringBuilder sb = new StringBuilder();
        Deque<Character> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        queue.add('A');
        while (!queue.isEmpty()) {
            char now = queue.poll();
            if (now == '.') continue;
            Node node = tree.get(now);
            if (visited[node.getData() - 'A']) {
                sb.append(node.getData());
                continue;
            }
            visited[node.getData() - 'A'] = true;
            for (Order o : orders) {
                if (o == Order.NOW) queue.addFirst(now);
                else if (o == Order.LEFT) queue.addFirst(node.getLeft());
                else if (o == Order.RIGHT) queue.addFirst(node.getRight());
            }
        }
        sb.append("\n");
        return sb.toString();
    }

    private enum Order {
        NOW, LEFT, RIGHT
    }

    private static class Node {
        private final char data;
        private final char left;
        private final char right;

        Node(char data, char left, char right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public char getData() {
            return data;
        }

        public char getLeft() {
            return left;
        }

        public char getRight() {
            return right;
        }
    }
}
