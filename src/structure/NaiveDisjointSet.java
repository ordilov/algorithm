package structure;

import java.util.ArrayList;
import java.util.List;

// 트리를 이용해 상호 배타적 집합을 구현한다.
public class NaiveDisjointSet {
    List<Integer> parent;
    public NaiveDisjointSet(int n){
        this.parent = new ArrayList<>();
        for(int i = 0; i < n; i++){
            parent.add(i);
        }
    }
    // u가 속한 트리의 루트를 반환한다.
    int find(int u) {
        if(u == parent.get(u)) return u;
        return find(parent.get(u));
    }
    // u가 속한 트리와 v가 속한 트리를 합친다.
    void merge(int u, int v){
        u = find(u);
        v = find(v);
        // u와 v가 이미 같은 트리에 속하는 경우를 걸러낸다.
        if(u == v) return;
        parent.set(u, v);
    }
}
