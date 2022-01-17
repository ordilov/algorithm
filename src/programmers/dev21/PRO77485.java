package programmers.dev21;

// [행렬 테두리 회전하기] https://programmers.co.kr/learn/courses/30/lessons/77485?language=java
public class PRO77485 {

    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int[][] m = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                m[i][j] = i * columns + j + 1;
            }
        }

        int index = 0;
        for (int[] query : queries) {
            int sx = query[0] - 1;
            int sy = query[1] - 1;
            int ex = query[2] - 1;
            int ey = query[3] - 1;
            int min = rows * columns;

            int[] up = new int[ey - sy + 1];
            int[] down = new int[ey - sy + 1];
            int[] right = new int[ex - sx + 1];
            int[] left = new int[ex - sx + 1];

            for (int i = sy; i <= ey; i++) {
                up[i - sy] = m[sx][i];
                down[i - sy] = m[ex][i];
                min = Math.min(Math.min(m[ex][i], m[sx][i]), min);
            }

            for (int i = sx; i <= ex; i++) {
                right[i - sx] = m[i][ey];
                left[i - sx] = m[i][sy];
                min = Math.min(Math.min(m[i][ey], m[i][sy]), min);
            }

            for (int i = sy; i <= ey; i++) {
                if(i != sy) m[sx][i] = up[i - sy - 1];
                if(i != ey) m[ex][i] = down[i - sy + 1];
            }

            for (int i = sx; i <= ex; i++) {
                if(i != sx) m[i][ey] = right[i - sx - 1];
                if(i != ex) m[i][sy] = left[i - sx + 1];
            }
            answer[index++] = min;
        }

        return answer;
    }
}
