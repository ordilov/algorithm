package programmers.practice;

public class PRO12923 {

    public int[] solution(long begin, long end) {
        int[] answer = new int[(int) (end - begin + 1)];
        for (long i = begin; i <= end; i++) {
            answer[(int) (i - begin)] = divisor(i);
        }
        return answer;
    }

    private int divisor(long n) {
        if(n == 1) {
            return 0;
        }

        for (long i = 2; i <= Math.sqrt(n); i++) {
            if (n % i != 0 || n / i > 10_000_000) {
                continue;
            }
            return (int) (n / i);
        }

        return 1;
    }

}