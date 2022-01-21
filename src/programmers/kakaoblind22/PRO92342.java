package programmers.kakaoblind22;

import java.util.Arrays;

//[양궁대회] https://programmers.co.kr/learn/courses/30/lessons/92342?language=java
public class PRO92342 {

    int[] temp = new int[11];
    int[] fixed = new int[11];
    int maxScore = Integer.MIN_VALUE;
    int minNum = 10;
    int minCount = 0;

    public int[] solution(int n, int[] info) {
        dfs(info, n, 10, 0, 0);
        if (maxScore <= 0) return new int[]{-1};
        return fixed;
    }

    private void dfs(int[] info, int n, int min, int sum, int now) {
        if (now > 10) {
            if (sum < maxScore) return;
            if (sum == maxScore) {
                if (min > minNum) return;
                if (min == minNum && temp[min] < minCount) return;
            }

            if (n > 0) temp[10] = n;
            maxScore = sum;
            minNum = min;
            minCount = temp[min];
            fixed = Arrays.copyOf(temp, temp.length);
            return;
        }

        int shot = info[now] + 1;
        int score = 10 - now;

        if (n - shot >= 0) {
            temp[now] = shot;
            dfs(info, n - shot, score, sum + score, now + 1);
        }

        temp[now] = 0;
        int peachScore = info[now] > 0 ? score : 0;
        dfs(info, n, min, sum - peachScore, now + 1);
    }
}
