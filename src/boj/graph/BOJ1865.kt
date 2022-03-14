package boj.graph

import java.util.*

//[웜홀] https://www.acmicpc.net/problem/1865

private lateinit var graph: Array<MutableList<Pair<Int, Int>>>
private lateinit var dist: IntArray
private const val INF = 987654321

fun main() {
    val tc = readLine()!!.toInt()
    val sb = StringBuilder()
    repeat(tc) {
        val (n, m, w) = readLine()!!.split(" ").map { it.toInt() }

        graph = Array(n + 1) { mutableListOf() }
        for (i in 0..n)
            dist = IntArray(n + 1) { INF }

        repeat(m) {
            val (s, e, t) = readLine()!!.split(" ").map { it.toInt() }
            graph[s].add(Pair(e, t))
            graph[e].add(Pair(s, t))
        }

        repeat(w) {
            val (s, e, t) = readLine()!!.split(" ").map { it.toInt() }
            graph[s].add(Pair(e, -t))
        }

        var timeTravel = false
        for (i in 1..n) {
            if (!bellmanFord(i, n)) continue
            timeTravel = true
            break
        }
        sb.append(if (timeTravel) "YES" else "NO").append("\n")
    }
    println(sb)
}

private fun bellmanFord(start: Int, n: Int): Boolean {
    Arrays.fill(dist, INF)
    dist[start] = 0
    var update = false

    for (i in 1 until n) {
        update = false
        for (j in 1..n) {
            for (k in graph[j]) {
                if (dist[j] == INF || dist[k.first] <= dist[j] + k.second) continue
                dist[k.first] = dist[j] + k.second
                update = true
            }
        }
        if (!update) break
    }
    if (!update) return false

    for (i in 1..n) {
        for (k in graph[i]) {
            if (dist[i] == INF || dist[k.first] <= dist[i] + k.second) continue
            return true
        }
    }
    return false
}