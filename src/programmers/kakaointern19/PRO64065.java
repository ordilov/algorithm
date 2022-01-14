package programmers.kakaointern19;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

// [튜플] https://programmers.co.kr/learn/courses/30/lessons/64065?language=java
public class PRO64065 {

    public int[] solution(String s) {
        s = s.substring(1, s.length() - 1);
        Pattern bracket = Pattern.compile("\\{.*?\\}");
        Matcher matcher = bracket.matcher(s);
        List<List<Integer>> tuples = new ArrayList<>();
        Set<Integer> elements = new LinkedHashSet<>();
        while (matcher.find()) {
            String[] splitted = matcher.group().substring(1, matcher.group().length() - 1).split(",");
            List<Integer> tuple = Arrays.stream(splitted).map(Integer::parseInt).collect(Collectors.toList());
            tuples.add(tuple);
        }
        tuples.sort(Comparator.comparingInt(List::size));
        tuples.forEach(elements::addAll);
        return elements.stream().mapToInt(i -> i).toArray();
    }
}
