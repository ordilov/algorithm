package programmers.monthly1;

import java.util.ArrayList;
import java.util.List;

// [쿼드압축 후 개수 세기] https://programmers.co.kr/learn/courses/30/lessons/68936?language=java
public class PRO68936 {

    List<Integer> compression = new ArrayList<>();

    public int[] solution(int[][] arr) {
        dfs(0, 0, arr.length, arr);
        int zero = 0;
        for (int num : compression) {
            if (num == 0) zero++;
        }

        return new int[]{zero, compression.size() - zero};
    }

    private void dfs(int y, int x, int size, int[][] arr) {
        if (size == 1) {
            compression.add(arr[y][x]);
            return;
        }
        boolean allMatched = true;
        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                if (arr[i][j] != arr[y][x]) {
                    allMatched = false;
                    break;
                }
            }
        }
        if (allMatched) {
            compression.add(arr[y][x]);
            return;
        }
        dfs(y, x, size / 2, arr);
        dfs(y + size / 2, x, size / 2, arr);
        dfs(y, x + size / 2, size / 2, arr);
        dfs(y + size / 2, x + size / 2, size / 2, arr);
    }
}
