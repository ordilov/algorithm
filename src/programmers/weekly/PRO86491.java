package programmers.weekly;

// [최소직사각형] https://programmers.co.kr/learn/courses/30/lessons/86491?language=java
public class PRO86491 {

    public int solution(int[][] sizes) {
        // Strategy
        /*
        만약 더 큰 애들을 왼쪽으로 몰았다면?
        무조건 두 값 중에 더 큰 애들만 왼쪽에 있게 된다.
        그리고 그 중에서 제일 큰 값을 뽑으면 그 길이에서 최대다.
        */
        int maxWide = 0;
        int maxNarrow = 0;
        for (int[] card : sizes) {
            int wideSize = Math.max(card[0], card[1]);
            int narrowSize = Math.min(card[0], card[1]);
            maxWide = Math.max(wideSize, maxWide);
            maxNarrow = Math.max(narrowSize, maxNarrow);
        }
        return maxWide * maxNarrow;
    }
}
