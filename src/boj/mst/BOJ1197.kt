package boj.mst

//[최소 스패닝 트리] https://www.acmicpc.net/problem/1197
fun main() {
    val (v, e) = readLine()!!.split(" ").map { it.toInt() }
    val graph: MutableMap<Pair<Int, Int>, Int> = mutableMapOf()

    for (i in 1..e) {
        val (a, b, w) = readLine()!!.split(" ").map { it.toInt() }
        val exists = graph[Pair(a, b)]
        if (exists == null || exists > w) {
            graph[Pair(a, b)] = w
            graph[Pair(b, a)] = w
        }
    }
    var result = 0L
    val parent = IntArray(v + 1) { it }
    val rank = IntArray(v + 1)
    run {
        graph.toList().sortedBy { (_, v) -> v }.forEach() { (u, t) ->
            val x = find(u.first, parent)
            val y = find(u.second, parent)
            if (x == y) return@forEach
            union(x, y, parent, rank)
            result += t
        }
    }
    println(result)
}

fun union(rootA: Int, rootB: Int, parent: IntArray, rank: IntArray) {
    if (rank[rootA] < rank[rootB]) {
        parent[rootA] = rootB
    } else {
        parent[rootB] = rootA
        if (rank[rootA] == rank[rootB]) rank[rootA]++
    }
}

fun find(a: Int, parent: IntArray): Int {
    var root = a
    while (parent[root] != root) {
        root = parent[root]
    }
    return root
}