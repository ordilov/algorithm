package programmers.winter18;

// [방문 길이] https://programmers.co.kr/learn/courses/30/lessons/49994?language=java#
public class PRO49994 {

    private static final int MAX_X = 10, MAX_Y = 10, MIN_X = 0, MIN_Y = 0;

    public int solution(String dirs) {
        boolean[][][] visited = new boolean[10][10][2];
        int answer = 0, y = 5, x = 5;
        for(String dir : dirs.split("")){
            DIRECTION d = DIRECTION.valueOf(dir);
            int vertical = d.vertical;
            int ny = y + d.y;
            int nx = x + d.x;
            if(nx > MAX_X || nx < MIN_X || ny > MAX_Y || ny < MIN_Y) continue;
            int pointY = Math.max(y, ny);
            int pointX = Math.max(x, nx);
            y = ny;
            x = nx;
            if(visited[pointY][pointX][vertical]) continue;
            visited[pointY][pointX][vertical] = true;
            answer++;
        }
        return answer;
    }

    enum DIRECTION {
        U(1, 0, 0), D(-1, 0, 0), R(0, 1, 1), L(0, -1, 1);
        final int y, x, vertical;
        DIRECTION(int y, int x, int vertical){
            this.y = y;
            this.x = x;
            this.vertical = vertical;
        }
    }
}
