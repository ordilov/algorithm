package programmers.monthly1;

// [이진 변환 반복하기] https://programmers.co.kr/learn/courses/30/lessons/70129?language=java
public class PRO70129 {
    public int[] solution(String s) {
        int transitionCount = 0;
        int zeroCount = 0;
        while (!s.equals("1")) {
            int length = s.length();
            s = s.replaceAll("0", "");
            zeroCount += length - s.length();
            s = Integer.toBinaryString(s.length());
            transitionCount++;
        }
        return new int[]{transitionCount, zeroCount};
    }
}
