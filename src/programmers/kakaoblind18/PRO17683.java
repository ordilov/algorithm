package programmers.kakaoblind18;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

// [[3차] 방금그곡] https://programmers.co.kr/learn/courses/30/lessons/17683?language=java#
public class PRO17683 {

    public String solution(String m, String[] musicinfos) throws ParseException {
        /*
        musicinfos 로 재생된 거 만들기
        m과 비교해서 일치하는 것 찾기
        일치하면 재생된 시간 제일 긴것
        재생된 시간도 같으면 먼저 입력된 음악 제목
        */
        List<String> list = new ArrayList<>();
        int max = 0;
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        m = translateNoSharp(m);
        for (String musicinfo : musicinfos) {
            String[] split = musicinfo.split(",");
            long startDate = format.parse(split[0]).getTime();
            long endDate = format.parse(split[1]).getTime();
            int minute = (int) TimeUnit.MILLISECONDS.toMinutes(endDate - startDate);

            String title = split[2];
            String score = split[3];
            score = translateNoSharp(score);
            StringBuilder melody = new StringBuilder();
            int length = minute;
            while (length > 0) {
                melody.append(score, 0, Math.min(length, score.length()));
                length -= score.length();
            }
            int idx = melody.indexOf(m);
            if (idx == -1) continue;
            if (minute < max) continue;
            if (minute > max) {
                list.clear();
                max = minute;
            }
            list.add(title);
        }
        String answer = "(None)";
        if (list.size() > 0) answer = list.get(0);
        return answer;
    }

    private String translateNoSharp(String score) {
        score = score.replaceAll("C#", "c");
        score = score.replaceAll("D#", "d");
        score = score.replaceAll("F#", "f");
        score = score.replaceAll("G#", "g");
        score = score.replaceAll("A#", "a");
        return score;
    }

}
