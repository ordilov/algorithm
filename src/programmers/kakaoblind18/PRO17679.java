package programmers.kakaoblind18;

// [[1차] 프렌즈4블록] https://programmers.co.kr/learn/courses/30/lessons/17679?language=java#
public class PRO17679 {

    boolean[][] visited;
    char[][] blocks;
    int[] dy = {0, 1, 0, 1};
    int[] dx = {0, 0, 1, 1};

    public int solution(int m, int n, String[] board) {
        blocks = new char[m][];
        int answer = 0;
        for (int i = 0; i < board.length; i++)
            blocks[i] = board[i].toCharArray();

        while (true) {
            int count = 0;
            visited = new boolean[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (blocks[i][j] == Character.MIN_VALUE) continue;
                    if (!isMatched(i, j)) continue;
                    count += checkVisited(i, j);
                }
            }
            if (count == 0) break;
            removeBlocks(m, n);
            arrangeBlocks(m, n);
            answer += count;
        }
        return answer;
    }

    private void removeBlocks(int m, int n) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j])
                    blocks[i][j] = Character.MIN_VALUE;
            }
        }
    }

    private void arrangeBlocks(int m, int n) {
        for (int j = 0; j < n; j++) {
            for (int i = m - 1; i >= 0; i--) {
                if (blocks[i][j] != Character.MIN_VALUE) continue;
                int next = i - 1;
                while (next >= 0 && blocks[next][j] == Character.MIN_VALUE) {
                    next--;
                }
                if (next < 0) break;
                blocks[i][j] = blocks[next][j];
                blocks[next][j] = Character.MIN_VALUE;
            }
        }
    }

    private boolean isMatched(int y, int x) {
        for (int i = 1; i < 4; i++) {
            int ny = dy[i] + y;
            int nx = dx[i] + x;
            if (ny >= blocks.length || nx >= blocks[0].length) return false;
            if (blocks[ny][nx] != blocks[y][x]) return false;
        }
        return true;
    }

    private int checkVisited(int y, int x) {
        int visitCount = 0;
        for (int i = 0; i < 4; i++) {
            int ny = dy[i] + y;
            int nx = dx[i] + x;
            if (!visited[ny][nx]) visitCount++;
            visited[ny][nx] = true;
        }
        return visitCount;
    }
}
