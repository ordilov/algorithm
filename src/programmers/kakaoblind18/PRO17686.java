package programmers.kakaoblind18;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// [[3차] 파일명 정렬] https://programmers.co.kr/learn/courses/30/lessons/17686?language=java
public class PRO17686 {

    public String[] solution(String[] files) {
        /*
        HEAD 대소문자 구분 X
        앞에 0 무시 숫자 순
        TAIL은 순서에 영향 X
        */
        Map<String, FileName> fileNames = new LinkedHashMap<>();
        Pattern numberPattern = Pattern.compile("[0-9]+");
        for (String file : files) {
            Matcher matcher = numberPattern.matcher(file);
            matcher.find();
            String head = file.substring(0, matcher.start()).toLowerCase();
            int number = Integer.parseInt(matcher.group());
            fileNames.put(file, new FileName(head, number));
        }

        Arrays.sort(files, (o1, o2) -> {
            FileName fileName1 = fileNames.get(o1);
            FileName fileName2 = fileNames.get(o2);

            if (!fileName1.head.equals(fileName2.head))
                return fileName1.head.compareTo(fileName2.head);

            return fileName1.number - fileName2.number;
        });

        return files;
    }

    static class FileName {
        String head;
        int number;

        public FileName(String head, int number) {
            this.head = head;
            this.number = number;
        }
    }

}
