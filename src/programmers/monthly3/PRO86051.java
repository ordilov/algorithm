package programmers.monthly3;

// [없는 숫자 더하기] https://programmers.co.kr/learn/courses/30/lessons/86051?language=java
public class PRO86051 {

    public int solution(int[] numbers) {
        /*
        0~9 까지 더한 값은 45이다.
        45에서 numbers 를 더한 값을 뺴면 나머지를 더한 값이다.
        */
        int maxSum = 45;
        int sum = 0;
        for(int number: numbers)
            sum += number;
        return maxSum - sum;
    }

}
