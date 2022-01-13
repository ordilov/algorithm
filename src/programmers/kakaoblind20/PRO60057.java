package programmers.kakaoblind20;

// [문자열 압축] https://programmers.co.kr/learn/courses/30/lessons/60057?language=java#
public class PRO60057 {

    public int solution(String s) {
        int answer = Integer.MAX_VALUE;
        if (s.length() == 1) answer = 1;
        for (int i = 1; i <= s.length() / 2; i++) {
            int index = 0;
            int count = 1;
            StringBuilder compress = new StringBuilder();
            String before = "";
            while (true) {
                String now = s.substring(index, index + i);
                if (now.equals(before)) count++;
                else {
                    if (count > 1) compress.append(count);
                    compress.append(before);
                    before = now;
                    count = 1;
                }
                index += i;
                if (index + i > s.length()) {
                    if (count > 1) compress.append(count);
                    compress.append(before);
                    compress.append(s.substring(index));
                    break;
                }
            }
            answer = Math.min(answer, compress.length());
        }
        return answer;
    }
}
