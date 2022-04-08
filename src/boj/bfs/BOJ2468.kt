package boj.bfs

import java.util.*

// [안전 영역] https://www.acmicpc.net/problem/2468

private lateinit var map: Array<IntArray>
private lateinit var visited: Array<BooleanArray>
private val dir = arrayOf(Pair(0, 1), Pair(0, -1), Pair(1, 0), Pair(-1, 0))
fun main() {
    val n = readLine()!!.toInt()
    map = Array(n) { IntArray(n) }
    visited = Array(n) { BooleanArray(n) }
    var max = 0
    var min = 100
    for (i in 0 until n) {
        map[i] = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
        min = minOf(min, map[i].minOf { it })
        max = maxOf(max, map[i].maxOf { it })
    }

    var answer = 1
    for (k in min until max) {
        var count = 0
        visited.forEach { Arrays.fill(it, false) }
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (map[i][j] <= k || visited[i][j]) continue
                count++
                bfs(i, j, k)
            }
        }
        answer = maxOf(answer, count)
    }
    println(answer)
}

private fun bfs(y: Int, x: Int, h: Int) {
    val queue = LinkedList<Pair<Int, Int>>()
    queue.add(Pair(y, x))
    visited[y][x] = true
    while (queue.isNotEmpty()) {
        val (cy, cx) = queue.poll()
        for (i in 0 until 4) {
            val ny = cy + dir[i].first
            val nx = cx + dir[i].second
            if (ny < 0 || ny >= map.size || nx < 0 || nx >= map.size || map[ny][nx] <= h || visited[ny][nx]) continue
            visited[ny][nx] = true
            queue.add(Pair(ny, nx))
        }
    }
}