package programmers.monthly3;

import java.util.ArrayList;
import java.util.List;

// [빛의 경로 사이클] https://programmers.co.kr/learn/courses/30/lessons/86052?language=java#
public class PRO86052 {

    // S, L, R 순
    // 좌, 상, 우, 하 순
    int[][] dy = {{0, 1, 0, -1}, {-1, 0, 1, 0}, {1, 0, -1, 0}};
    int[][] dx = {{1, 0, -1, 0}, {0, 1, 0, -1}, {0, -1, 0, 1}};
    int[][] dd = {{0, 1, 2, 3}, {3, 0, 1, 2}, {1, 2, 3, 0}};
    boolean[][][] visited;
    char[][] dir;
    int count;

    public int[] solution(String[] grid) {
        /*
        시작할 수 있는 위치 모든 점 * 4
        */
        dir = new char[grid.length][];
        for(int i = 0; i < grid.length; i++){
            dir[i] = grid[i].toCharArray();
        }
        visited = new boolean[dir.length][dir[0].length][4];
        List<Integer> list = new ArrayList<>();

        for(int i = 0; i < dir.length; i++){
            for(int j = 0; j < dir[i].length; j++){
                for(int k = 0; k < 4; k++){
                    if(visited[i][j][k]) continue;
                    count = 0;
                    dfs(i, j, k);
                    list.add(count);
                }
            }
        }
        return list.stream().sorted().mapToInt(Integer::intValue).toArray();
    }

    void dfs(int y, int x, int d){

        while(!visited[y][x][d]){
            visited[y][x][d] = true;
            count++;
            int m = Direction.valueOf(String.valueOf(dir[y][x])).value;
            y = (y + dy[m][d] + dir.length) % dir.length;
            x = (x + dx[m][d] + dir[0].length) % dir[0].length;
            d = dd[m][d];
        }
    }

    enum Direction{
        S(0), L(1), R(2);
        private final int value;
        Direction(int value) {
            this.value = value;
        }
    }

}
