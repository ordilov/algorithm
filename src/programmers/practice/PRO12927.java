package programmers.practice;

import java.util.*;

//[야근 지수] https://school.programmers.co.kr/learn/courses/30/lessons/12927
class PRO12927 {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        pq.addAll(Arrays.stream(works).boxed().toList());

        while (n > 0 && !pq.isEmpty()) {
            int remain = pq.poll();
            if (remain == 0) break;
            int next = (pq.isEmpty() || pq.peek() == 0) ? 1 : pq.peek();
            int diff = remain - next + 1;
            int work = Math.min(diff, n);
            n -= work;
            pq.offer(remain - work);
        }

        return pq.stream().mapToLong(i -> (long) i * i).sum();
    }

    public static void main(String[] args) {
        PRO12927 pro12927 = new PRO12927();
        int n = 4;
        int[] works = {4, 3, 3};
        System.out.println(pro12927.solution(n, works));
    }
}