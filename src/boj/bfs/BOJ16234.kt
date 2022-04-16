package boj.bfs

import kotlin.math.abs

// [인구 이동] https://www.acmicpc.net/problem/16234
private lateinit var map: Array<IntArray>
private lateinit var visited: Array<BooleanArray>
private var n = 0
private var l = 0
private var r = 0
private val dir = arrayOf(Pair(0, 1), Pair(0, -1), Pair(1, 0), Pair(-1, 0))
fun main() {
    readLine()!!.split(" ").map { it.toInt() }.let {
        n = it[0]
        l = it[1]
        r = it[2]
    }
    map = Array(n) { IntArray(n) }
    for (i in 0 until n) {
        map[i] = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    }

    var day = 0
    var move = true
    while (move) {
        move = false
        visited = Array(n) { BooleanArray(n) }

        for (i in 0 until n) {
            for (j in 0 until n) {
                if (visited[i][j]) continue
                val count = bfs(i, j)
                if (count == 1) continue
                move = true
            }
        }

        if(move) day++
    }
    println(day)
}

private fun bfs(x: Int, y: Int): Int {
    val queue = ArrayDeque<Pair<Int, Int>>()
    val trail = ArrayDeque<Pair<Int, Int>>()
    queue.add(Pair(x, y))
    visited[x][y] = true
    var sum = 0
    while (queue.isNotEmpty()) {
        val (i, j) = queue.removeFirst()
        trail.add(Pair(i, j))
        sum += map[i][j]
        for (k in 0 until 4) {
            val dx = i + dir[k].first
            val dy = j + dir[k].second
            if (dx < 0 || dx >= n || dy < 0 || dy >= n || visited[dx][dy]) continue
            val diff = abs(map[i][j] - map[dx][dy])
            if (diff < l || diff > r) continue
            visited[dx][dy] = true
            queue.add(Pair(dx, dy))
        }
    }

    val count = trail.size
    while (trail.isNotEmpty()) {
        val (i, j) = trail.removeFirst()
        map[i][j] = sum / count
    }
    return count
}