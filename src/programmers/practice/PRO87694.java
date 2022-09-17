package programmers.practice;

import java.util.*;

public class PRO87694 {

    private final int[] dx = {-1, 1, 0, 0};
    private final int[] dy = {0, 0, -1, 1};
    private final boolean[][] map = new boolean[102][102];
    private final List<Rectangle> rectList = new ArrayList<>();

    static class Rectangle {
        int x1, x2, y1, y2;

        public Rectangle(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        for (int[] rec : rectangle) {
            int sx = rec[0] * 2;
            int sy = rec[1] * 2;
            int ex = rec[2] * 2;
            int ey = rec[3] * 2;

            for (int y = sy; y <= ey; y++) {
                for (int x = sx; x <= ex; x++) {
                    map[y][x] = true;
                }
            }
            rectList.add(new Rectangle(sx, sy, ex, ey));
        }

        return bfs(map, characterX * 2, characterY * 2, itemX * 2, itemY * 2);
    }

    private int bfs(boolean[][] map, int x, int y, int tx, int ty) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y, 1});
        map[y][x] = false;
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int px = p[0];
            int py = p[1];
            int move = p[2];

            if (px == tx && py == ty) {
                return (move / 2);
            }

            for (int i = 0; i < 4; i++) {
                int nx = px + dx[i];
                int ny = py + dy[i];
                if (map[ny][nx] && isBoundary(nx, ny)) {
                    map[ny][nx] = false;
                    q.add(new int[]{nx, ny, move + 1});
                }
            }
        }
        return -1;
    }

    private boolean isBoundary(int x, int y) {
        return rectList.stream().noneMatch(r -> r.x1 < x && r.y1 < y && r.x2 > x && r.y2 > y);
    }

    public static void main(String[] args) {
        PRO87694 pro87694 = new PRO87694();
        int[][] rectangle = {{1, 1, 7, 4}, {3, 2, 5, 5}, {4, 3, 6, 9}, {2, 6, 8, 8}};
        int characterX = 1;
        int characterY = 3;
        int itemX = 7;
        int itemY = 8;
        int result = pro87694.solution(rectangle, characterX, characterY, itemX, itemY);
        System.out.println(result);
    }
}
