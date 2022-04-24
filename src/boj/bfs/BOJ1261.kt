package boj.bfs

import java.util.PriorityQueue

//[알고스팟] https://www.acmicpc.net/problem/1261
private val dir = arrayOf(
    Pair(0, 1),
    Pair(0, -1),
    Pair(1, 0),
    Pair(-1, 0)
)

fun main() {
    val (m, n) = readLine()!!.split(" ").map { it.toInt() }
    val map = Array(n) { readLine()!!.toCharArray().map { it == '1'}.toBooleanArray() }
    println(bfs(n, m, map))
}

private fun bfs(n: Int, m: Int, map: Array<BooleanArray>): Int {
    val queue = PriorityQueue<Node1261>(compareBy { it.wall })
    queue.offer(Node1261(0, 0, 0))

    val visited = Array(n) { BooleanArray(m) }
    visited[0][0] = true

    while (queue.isNotEmpty()) {
        val (y, x, w) = queue.poll()

        if (y == n - 1 && x == m - 1) {
            return w
        }

        for (i in 0 until 4) {
            val ny = y + dir[i].first
            val nx = x + dir[i].second
            if (ny < 0 || nx < 0 || ny >= n || nx >= m || visited[ny][nx]) continue
            visited[ny][nx] = true
            if (map[ny][nx]) queue.offer(Node1261(ny, nx, w + 1))
            else queue.offer(Node1261(ny, nx, w))
        }
    }
    return 0
}

private data class Node1261(val y: Int, val x: Int, val wall: Int)