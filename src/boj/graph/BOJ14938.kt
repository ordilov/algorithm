package boj.graph

import java.util.*

// [서강그라운드] https://www.acmicpc.net/problem/14938

private lateinit var graph: Array<MutableList<Road>>
private lateinit var items: List<Int>
private var n: Int = 0
private var m: Int = 0
private var r: Int = 0
private const val INF = 1501
fun main() {
    val line = readLine()!!.split(" ").map { it.toInt() }
    n = line[0]
    m = line[1]
    r = line[2]
    graph = Array(n + 1) { mutableListOf() }
    items = readLine()!!.split(" ").map { it.toInt() }
    repeat(r) {
        val (a, b, i) = readLine()!!.split(" ").map { it.toInt() }
        graph[a].add(Road(b, i))
        graph[b].add(Road(a, i))
    }

    var max = 0
    for (i in 1..n) {
        max = maxOf(max, dijkstra(i))
    }
    println(max)
}

private data class Road(val dest: Int, val distance: Int)

private fun dijkstra(start: Int): Int {
    val dist = IntArray(n + 1) { INF }
    dist[start] = 0
    val queue = PriorityQueue<Road>(compareBy { it.dest })
    queue.add(Road(start, 0))
    while (queue.isNotEmpty()) {
        val (now, distance) = queue.poll()
        if (dist[now] < distance) continue

        for (road in graph[now]) {
            val (next, d) = road
            val nextDistance = distance + d
            if (nextDistance >= dist[next] || nextDistance > m) continue
            dist[next] = nextDistance
            queue.add(Road(next, dist[next]))
        }
    }
    return dist.filter { it != INF }.sum()
}