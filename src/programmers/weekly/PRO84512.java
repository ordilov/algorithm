package programmers.weekly;

// [모음사전] https://programmers.co.kr/learn/courses/30/lessons/84512?language=java
public class PRO84512 {

    public int solution(String word) {
        /*
        I 결과를 봤을 때 숫자 구간 나누기 어려워보임
        완전 탐색으로 찾기
        */
        int answer = 1;
        String c = "A";
        while(!c.equals(word)){
            if(c.length() < 5){
                c = add(c);
            }
            else{
                c = upgrade(c, 4);
            }
            answer++;
        }
        return answer;
    }

    public static String upgrade(String s, int idx) {
        if(s.charAt(idx) == 'U') {
            s = upgrade(s.substring(0, idx), idx - 1);
        } else {
            s = s.substring(0, idx) + Word.valueOf(s.substring(idx, idx + 1)).next().name();
        }
        return s;
    }

    public static String add(String s){
        return s + "A";
    }

    enum Word {
        A, E, I, O, U;

        public Word next(){
            return Word.values()[ordinal() + 1];
        }
    }
}
