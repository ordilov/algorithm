package programmers.weekly;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// [교점에 별 만들기] https://programmers.co.kr/learn/courses/30/lessons/87377?language=java
public class PRO87377 {

    public String[] solution(int[][] line) {
        /*
        Ax + By + E = 0
        Cx + Dy + F = 0
        */
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
        Set<Pair> set = new HashSet<>();
        for (int i = 0; i < line.length; i++) {
            for (int j = i + 1; j < line.length; j++) {
                if (i == j) continue;
                long A = line[i][0];
                long B = line[i][1];
                long E = line[i][2];
                long C = line[j][0];
                long D = line[j][1];
                long F = line[j][2];

                long down = A * D - B * C;
                long ux = B * F - E * D;
                long uy = E * C - A * F;
                if (down == 0 || ux % down != 0 || uy % down != 0) continue;
                minX = Math.min(minX, (int) (ux / down));
                maxX = Math.max(maxX, (int) (ux / down));
                minY = Math.min(minY, (int) (uy / down));
                maxY = Math.max(maxY, (int) (uy / down));
                set.add(new Pair((int) (uy / down), (int) (ux / down)));
            }
        }
        int x = (int) (maxX - minX + 1);
        int y = (int) (maxY - minY + 1);
        char[][] map = new char[y][x];
        String[] answer = new String[y];

        for (char[] m : map)
            Arrays.fill(m, '.');

        for (Pair p : set)
            map[p.y - minY][p.x - minX] = '*';

        for (int i = 0; i < y; i++) {
            answer[i] = new String(map[y - 1 - i]);
        }

        return answer;
    }

    static class Pair implements Comparable<Pair> {
        int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return y == pair.y && x == pair.x;
        }

        @Override
        public int hashCode() {
            return Objects.hash(y, x);
        }

        @Override
        public int compareTo(Pair o) {
            if (y == o.y) {
                return x - o.x;
            }
            return Integer.compare(y, o.y);
        }
    }

}
