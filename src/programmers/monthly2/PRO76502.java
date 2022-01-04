package programmers.monthly2;

import java.util.Stack;

// [괄호 회전하기] https://programmers.co.kr/learn/courses/30/lessons/76502?language=java
public class PRO76502 {

    public int solution(String s) {
        int answer = 0;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char[] now = (s.substring(i) + s.substring(0, i)).toCharArray();
            stack.clear();
            stack.push(now[0]);
            for (char c : now) {
                if (stack.empty()) {
                    stack.push(c);
                    continue;
                }
                if (stack.peek() == '[' && c == ']') {
                    stack.pop();
                } else if (stack.peek() == '(' && c == ')') {
                    stack.pop();
                } else if (stack.peek() == '{' && c == '}') {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
            if (stack.empty()) answer++;
        }
        return answer;
    }
}
