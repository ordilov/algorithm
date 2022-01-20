package programmers.kakaoblind22;

// [k진수에서 소수 개수 구하기] https://programmers.co.kr/learn/courses/30/lessons/92335?language=java
public class PRO92335 {

    public int solution(int n, int k) {
        int answer = 0;
        String[] x = toRadixString(n, k).split("[0]+");
        for (String c : x) {
            if (isPrime(Long.parseLong(c))) answer++;
        }

        return answer;
    }

    private boolean isPrime(long n) {
        if (n < 2) return false;
        if (n == 2) return true;
        for (long i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    private String toRadixString(int n, int k) {
        StringBuilder s = new StringBuilder();
        while (n > 0) {
            s.insert(0, n % k);
            n /= k;
        }
        return s.toString();
    }
}
