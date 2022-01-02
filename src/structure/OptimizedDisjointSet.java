package structure;

import java.util.ArrayList;
import java.util.List;

// 트리를 이용해 상호 배타적 집합을 구현한다.
public class OptimizedDisjointSet extends NaiveDisjointSet{

  List<Integer> rank;

  public OptimizedDisjointSet(int n) {
    super(n);
    rank = new ArrayList<>(n);
    for(int i = 0; i < n; i++)
      rank.add(1);
  }

  @Override
  public int find(int u){
    if(u == parent.get(u)) return u;
    parent.set(u, find(parent.get(u)));
    return parent.get(u);
  }

  @Override
  public void merge(int u, int v) {
    u = find(u);
    v = find(v);
    if(u == v) return;
    if(rank.get(u) > rank.get(v)){
      v = swap(u, u = v);
    }
    parent.set(u, v);
    if(rank.get(u).equals(rank.get(v))){
      rank.set(v, rank.get(v));
    }
  }

  // y = swap(x, x = y)
  private int swap(int a, int b){
    return a;
  }
}