package programmers.kakaoblind19;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// [후보키] https://programmers.co.kr/learn/courses/30/lessons/42890?language=java
public class PRO42890 {
    boolean[] visited;
    Set<String> columns = new HashSet<>();
    Set<Set<String>> candidates = new HashSet<>();

    public int solution(String[][] relation) {
        visited = new boolean[relation[0].length];
        int answer = 0;
        for (int size = 1; size <= relation[0].length; size++) {
            answer += countCandidateKey(relation, 0, size);
        }
        return answer;
    }

    private int countCandidateKey(String[][] relation, int start, int size) {
        if (size == columns.size()) {
            if (relation.length != getTupleSize(relation)) return 0;
            for (Set<String> candidate : candidates) {
                if (columns.containsAll(candidate)) return 0;
            }
            candidates.add(new HashSet<>(columns));
            return 1;
        }

        int candidateKey = 0;
        for (int i = start; i < relation[0].length; i++) {
            if (visited[i]) continue;
            columns.add(String.valueOf(i));
            visited[i] = true;
            candidateKey += countCandidateKey(relation, i + 1, size);
            columns.remove(String.valueOf(i));
            visited[i] = false;
        }
        return candidateKey;
    }

    private int getTupleSize(String[][] relation) {
        String[] row = new String[relation.length];
        Arrays.fill(row, "");
        for (String column : columns) {
            for (int i = 0; i < relation.length; i++) {
                row[i] += relation[i][Integer.parseInt(column)];
            }
        }
        return new HashSet<>(Arrays.asList(row)).size();
    }
}
