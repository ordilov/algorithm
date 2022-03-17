package boj.graph

//[파티] https://www.acmicpc.net/problem/1238
private lateinit var v: BooleanArray
private lateinit var d: Array<IntArray>
private lateinit var map: Array<MutableList<Pair<Int, Int>>>
private var n: Int = 0

fun main() {
    val line = readLine()!!.split(" ")
    n = line[0].toInt()
    val m = line[1].toInt()
    val x = line[2].toInt()

    map = Array(n + 1) { mutableListOf() }
    repeat(m) {
        val (a, b, t) = readLine()!!.split(" ").map { it.toInt() }
        map[a].add(Pair(b, t))
    }

    d = Array(n + 1) { IntArray(n + 1) { Int.MAX_VALUE } }

    val dist = Array(n + 1) { 0 }
    for (i in 1..n) {
        v = BooleanArray(n + 1) { false }
        dijkstra(i)
        dist[i] = d[i][x]
    }
    var max = 0
    for (i in 1..n) {
        if (i == x) continue
        if (max > dist[i] + d[x][i]) continue
        max = dist[i] + d[x][i]
    }

    println(max)
}

private fun getSmallIndex(now: Int): Int {
    var min = Integer.MAX_VALUE
    var index = 0
    for (i in 1..n) {
        if (v[i] || d[now][i] > min) continue
        min = d[now][i]
        index = i
    }
    return index
}

private fun dijkstra(start: Int) {
    for (i in map[start]) {
        d[start][i.first] = i.second
    }
    v[start] = true
    for (i in 1..n) {
        val now = getSmallIndex(start)
        v[now] = true
        for (j in map[now]) {
            if (v[j.first]) continue
            if (d[start][now] + j.second < d[start][j.first]) {
                d[start][j.first] = d[start][now] + j.second
            }
        }
    }
}