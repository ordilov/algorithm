package programmers.kakaoblind18;

import java.util.ArrayList;
import java.util.List;

// [[1차] 뉴스 클러스터링] https://programmers.co.kr/learn/courses/30/lessons/17677?language=java
public class PRO17677 {

    public int solution(String str1, String str2) {
        List<String> s1 = getPartialString(str1.toLowerCase());
        List<String> s2 = getPartialString(str2.toLowerCase());
        List<String> intersect = getIntersect(s1, s2);
        List<String> union = getUnion(s1, s2, intersect);

        double divide = 1;
        if (intersect.size() != 0 || union.size() != 0)
            divide = (double) intersect.size() / (double) union.size();
        return (int) (divide * 65536);
    }

    private List<String> getUnion(List<String> s1, List<String> s2, List<String> intersect) {
        List<String> union = new ArrayList<>(intersect);
        getComplementary(s1, intersect, union);
        getComplementary(s2, intersect, union);
        return union;
    }

    private void getComplementary(List<String> set, List<String> intersect, List<String> union) {
        boolean[] matched = new boolean[intersect.size()];
        for (String s : set) {
            boolean complementary = true;
            for (int j = 0; j < intersect.size(); j++) {
                if (matched[j]) continue;
                if (s.equals(intersect.get(j))) {
                    matched[j] = true;
                    complementary = false;
                    break;
                }
            }
            if (complementary) union.add(s);
        }
    }

    private List<String> getIntersect(List<String> s1, List<String> s2) {
        List<String> intersect = new ArrayList<>();
        boolean[] matched = new boolean[s2.size()];
        for (String s : s1) {
            for (int j = 0; j < s2.size(); j++) {
                if (matched[j]) continue;
                if (s.equals(s2.get(j))) {
                    matched[j] = true;
                    intersect.add(s);
                    break;
                }
            }
        }
        return intersect;
    }

    private List<String> getPartialString(String str1) {
        List<String> s1 = new ArrayList<>();
        String alpha = "[a-zA-Z]+";
        for (int i = 0; i < str1.length() - 1; i++) {
            String part = str1.substring(i, i + 2);
            if (part.matches(alpha))
                s1.add(str1.substring(i, i + 2));
        }
        return s1;
    }
}
