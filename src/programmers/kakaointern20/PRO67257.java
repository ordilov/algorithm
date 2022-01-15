package programmers.kakaointern20;

import java.util.*;

// [수식 최대화] https://programmers.co.kr/learn/courses/30/lessons/67257?language=java
public class PRO67257 {

    public long solution(String expression) {
        /*
        숫자를 연산자 단위로 나눈다
        연산자 종류를 센다
        연산자 종류별 우선순위로 계산을 한다
        */
        char[] e = expression.toCharArray();
        List<Long> num = new ArrayList<>();
        List<Character> op = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < e.length; i++) {
            if (i == e.length - 1) num.add(Long.parseLong(expression.substring(start)));
            if (e[i] != '+' && e[i] != '*' && e[i] != '-') continue;
            num.add(Long.parseLong(expression.substring(start, i)));
            start = i + 1;
            op.add(e[i]);
        }
        List<List<Character>> order = new ArrayList<>();
        Set<Character> now = new LinkedHashSet<>();
        Set<Character> unique = new HashSet<>(op);
        getOrder(order, now, unique);

        long answer = 0;
        for (List<Character> o : order) {
            List<Long> tempNum = new ArrayList<>(num);
            List<Character> tempOp = new ArrayList<>(op);
            for (char oper : o) {
                int i = 0;
                while (i < tempOp.size()) {
                    if (tempOp.get(i) != oper) {
                        i++;
                        continue;
                    }
                    tempNum.set(i, calculate(tempNum.get(i), tempNum.get(i + 1), tempOp.get(i)));
                    tempOp.remove(i);
                    tempNum.remove(i + 1);
                }
            }
            answer = Math.max(answer, Math.abs(tempNum.get(0)));
        }
        return answer;
    }

    Long calculate(long a, long b, char op) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            default:
                return null;
        }
    }

    void getOrder(List<List<Character>> order, Set<Character> now, Set<Character> unique) {
        if (now.size() == unique.size()) {
            order.add(new ArrayList<>(now));
            return;
        }
        for (char op : unique) {
            if (now.contains(op)) continue;
            now.add(op);
            getOrder(order, now, unique);
            now.remove(op);
        }
    }
}
