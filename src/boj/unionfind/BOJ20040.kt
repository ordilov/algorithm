package boj.unionfind

// [사이클 게임] https://www.acmicpc.net/problem/20040
fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val parents = Array(n + 1) { it }
    val ranks = Array(n + 1) { 0 }

    var answer = 0
    for (i in 1..m) {
        val (a, b) = readLine()!!.split(" ").map { it.toInt() }
        if(find(parents, a) == find(parents, b)) {
            answer = i
            break
        }
        union(parents, ranks, a, b)
    }
    println(answer)
}

fun union(parents: Array<Int>, ranks: Array<Int>, x: Int, y: Int) {
    val xRoot = find(parents, x)
    val yRoot = find(parents, y)
    if (xRoot == yRoot) return
    if (ranks[xRoot] < ranks[yRoot]) {
        parents[xRoot] = yRoot
    } else if (ranks[xRoot] > ranks[yRoot]) {
        parents[yRoot] = xRoot
    } else {
        parents[yRoot] = xRoot
        ranks[xRoot]++
    }
}

fun find(parents: Array<Int>, x: Int): Int {
    if (parents[x] == x) return x
    return find(parents, parents[x])
}

