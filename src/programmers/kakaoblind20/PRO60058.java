package programmers.kakaoblind20;

import java.util.Stack;

// [괄호 변환] https://programmers.co.kr/learn/courses/30/lessons/60058?language=java
public class PRO60058 {

    public String solution(String p) {
        return split(p);
    }

    String split(String p) {
        if (p.equals("")) return "";
        Stack<Character> stack = new Stack<>();
        char[] b = p.toCharArray();
        int left = 0;
        int right = 0;
        String u = "";
        String v = "";
        for (int i = 0; i < b.length; i++) {
            if (b[i] == '(') left++;
            if (b[i] == ')') right++;
            if (!stack.empty() && stack.peek() == '(' && b[i] == ')') stack.pop();
            else stack.push(b[i]);

            if (left != right) continue;
            u = p.substring(0, i + 1);
            if (i + 1 != b.length)
                v = p.substring(i + 1);
            break;
        }
        if (stack.empty()) return u + split(v);
        return '(' + split(v) + ')' + reverse(u);
    }

    String reverse(String u) {
        u = u.substring(1, u.length() - 1);
        char[] ur = u.toCharArray();
        for (int i = 0; i < ur.length; i++) {
            if (ur[i] == '(') ur[i] = ')';
            else ur[i] = '(';
        }
        return new String(ur);
    }
}
