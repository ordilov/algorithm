package boj.bfs

import java.util.*

//[숨바꼭질] https://www.acmicpc.net/problem/6118
fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val map = Array(n + 1) { mutableListOf<Int>() }
    repeat(m) {
        val (a, b) = readLine()!!.split(" ").map { it.toInt() }
        map[a].add(b)
        map[b].add(a)
    }
    val q = LinkedList<Int>()
    val dist = ArrayList<Int>(n + 1)
    for (i in 0..n) {
        dist.add(Int.MAX_VALUE)
    }
    dist[0] = 0
    dist[1] = 0
    q.offer(1)
    while (q.isNotEmpty()) {
        val now = q.poll()
        for (i in 0 until map[now].size) {
            val c = map[now][i]
            if (dist[c] != Int.MAX_VALUE) continue
            dist[c] = dist[now] + 1
            q.offer(c)
        }
    }
    val max = dist.maxOrNull()!!
    val first = dist.indexOfFirst { it == max }
    val count = dist.count { it == max }
    println("$first $max $count")
}