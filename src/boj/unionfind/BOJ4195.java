package boj.unionfind;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//[친구 네트워크] https://www.acmicpc.net/problem/4195
public class BOJ4195 {

    private static final Map<String, String> parents = new HashMap<>();
    private static final Map<String, Integer> ranks = new HashMap<>();
    private static final Map<String, Integer> children = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder stringBuilder = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int f = Integer.parseInt(br.readLine());
            parents.clear();
            for (int j = 0; j < f; j++) {
                String[] s = br.readLine().split(" ");
                String f1 = s[0];
                String f2 = s[1];
                stringBuilder.append(union(f1, f2)).append("\n");
            }
        }
        bw.write(stringBuilder.toString());
        bw.flush();
        bw.close();
    }

    private static int union(String a, String b) {
        String pa = find(a);
        String pb = find(b);
        if (pa.equals(pb)) return children.get(pa);
        if (ranks.get(pa) > ranks.get(pb)) {
            String temp = pa;
            pa = pb;
            pb = temp;
        }
        parents.put(pa, pb);
        children.put(pb, children.get(pb) + children.get(pa));
        if(Objects.equals(ranks.get(pa), ranks.get(pb)))
            ranks.put(pb, ranks.get(pb) + 1);
        return children.get(pb);
    }

    private static String find(String a) {
        if(parents.get(a) == null) {
            parents.put(a, a);
            ranks.put(a, 0);
            children.put(a, 1);
        }
        if (parents.get(a).equals(a)) return a;
        String parent = find(parents.get(a));
        parents.put(a, parent);
        return parent;
    }
}
