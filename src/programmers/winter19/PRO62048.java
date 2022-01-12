package programmers.winter19;

// [멀쩡한 사각형] https://programmers.co.kr/learn/courses/30/lessons/62048?language=java
public class PRO62048 {
    public long solution(int w, int h) {
        long result = 0;
        double gradient = (double) w / h;
        for (long i = 1; i < h; i++) {
            result += Math.floor(gradient * i);
        }
        return result * 2;
    }
}
