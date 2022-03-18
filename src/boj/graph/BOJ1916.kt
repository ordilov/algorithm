package boj.graph

import java.util.*

//[최소비용 구하기] https://www.acmicpc.net/problem/1916
fun main() {
    val n = readLine()!!.toInt()
    val m = readLine()!!.toInt()
    val graph = Array(n + 1) { Array(n + 1) { Int.MAX_VALUE } }
    repeat(m) {
        val (a, b, c) = readLine()!!.split(" ").map { it.toInt() }
        graph[a][b] = minOf(graph[a][b], c)
    }
    val (start, end) = readLine()!!.split(" ").map { it.toInt() }

    val dist = Array(n + 1) { Int.MAX_VALUE }
    val visited = Array(n + 1) { false }
    val queue = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
    dist[start] = 0
    queue.add(Pair(start, 0))

    while (queue.isNotEmpty()) {
        val (now, cost) = queue.poll()
        visited[now] = true
        for (i in 1..n) {
            if (graph[now][i] == Int.MAX_VALUE || visited[i]) continue
            if (cost + graph[now][i] > dist[i]) continue
            dist[i] = minOf(dist[i], cost + graph[now][i])
            queue.add(Pair(i, dist[i]))
        }
    }

    println(dist[end])
}