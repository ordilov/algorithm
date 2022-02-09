package boj.graph

import java.util.*

// [미확인 도착지] https://www.acmicpc.net/problem/9370
fun main() {
    val T = readLine()!!.toInt()
    repeat(T) {
        val (n, m, t) = readLine()!!.split(" ").map { it.toInt() }
        val (s, g, h) = readLine()!!.split(" ").map { it.toInt() }
        val map = ArrayList<ArrayList<Pair<Int, Int>>>()
        repeat(n + 1) {
            map.add(ArrayList())
        }

        for (i in 0 until m) {
            val (a, b, d) = readLine()!!.split(" ").map { it.toInt() }
            var distance = d * 2
            if ((a == g && b == h) || (a == h && b == g)) {
                distance--
            }
            map[a].add(Pair(b, distance))
            map[b].add(Pair(a, distance))
        }

        val dist = Array(n + 1) { Integer.MAX_VALUE / 2 * 2 }
        dist[s] = 0
        val queue = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
        queue.add(Pair(s, 0))
        while (queue.isNotEmpty()) {
            val (v, d) = queue.poll()
            if (dist[v] < d) continue
            map[v].forEach { i ->
                if (dist[i.first] > dist[v] + i.second) {
                    dist[i.first] = dist[v] + i.second
                    queue.add(Pair(i.first, dist[i.first]))
                }
            }
        }

        val candidates = ArrayList<Int>()
        for (i in 0 until t) {
            val x = readLine()!!.toInt()
            candidates.add(x)
        }
        candidates.sort()
        candidates.filter { dist[it] % 2 == 1 }.forEach { print("$it ") }
    }
}

