package programmers.winter18;

// [스킬트리] https://programmers.co.kr/learn/courses/30/lessons/49993?language=java#fnref1
public class PRO49993 {

    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        for (String skill_tree : skill_trees) {
            char[] s = skill_tree.toCharArray();
            boolean[] learned = new boolean[skill.length()];
            boolean possible = true;
            for (char c : s) {
                int order = skill.indexOf(c);
                if (order == 0) learned[0] = true;
                if (order <= 0) continue;
                if (!learned[order - 1]) {
                    possible = false;
                    break;
                }
                learned[order] = true;
            }
            if (possible) answer++;
        }
        return answer;
    }
}
