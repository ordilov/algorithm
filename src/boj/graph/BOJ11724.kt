package boj.graph

//[연결 요소의 개수] https://www.acmicpc.net/problem/11724
fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val parent = IntArray(n + 1) { it }
    val rank = IntArray(n + 1) { 1 }
    repeat(m) {
        val (u, v) = readLine()!!.split(" ").map { it.toInt() }
        union(u, v, parent, rank)
    }
    repeat(n + 1) {
        find(it, parent)
    }
    println(parent.distinct().size - 1)
}

private fun union(u: Int, v: Int, p: IntArray, r: IntArray) {
    val pu = find(u, p)
    val pv = find(v, p)
    if (pu == pv) return
    if (r[pu] > r[pv]) {
        p[pv] = pu
    } else {
        p[pu] = pv
        if (r[pu] == r[pv]) r[pv]++
    }
}

private fun find(u: Int, p: IntArray): Int {
    if (p[u] == u) return u
    p[u] = find(p[u], p)
    return p[u]
}
