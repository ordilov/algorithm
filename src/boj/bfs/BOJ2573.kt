package boj.bfs

import java.util.*

// [빙산] https://www.acmicpc.net/problem/2573
private var n: Int = 0
private var m: Int = 0
private lateinit var map: Array<IntArray>
private lateinit var visited: Array<BooleanArray>
private var dir = arrayOf(
    intArrayOf(1, 0),
    intArrayOf(-1, 0),
    intArrayOf(0, 1),
    intArrayOf(0, -1)
)

fun main() {
    readLine()!!.split(' ').let {
        n = it[0].toInt()
        m = it[1].toInt()
    }
    map = Array(n) { IntArray(m) }
    visited = Array(n) { BooleanArray(m) }

    for (i in 0 until n) {
        readLine()!!.split(' ').let {
            for (j in 0 until m) {
                map[i][j] = it[j].toInt()
            }
        }
    }

    var count = 0
    var time = 0

    while (count < 2) {
        visited.forEach { Arrays.fill(it, false) }
        melt()
        count = 0

        visited.forEach { Arrays.fill(it, false) }
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (map[i][j] == 0 || visited[i][j]) continue
                bfs(i, j)
                count++
            }
        }
        if (count == 0) {
            time = 0
            break
        }
        time++
    }

    println(time)
}

private fun melt() {
    visited.forEach { Arrays.fill(it, false) }
    for (i in 0 until n) {
        for (j in 0 until m) {
            if (map[i][j] == 0) continue
            for (k in 0 until 4) {
                val nx = i + dir[k][0]
                val ny = j + dir[k][1]
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue
                if (map[nx][ny] != 0 || visited[nx][ny]) continue
                map[i][j]--
                if (map[i][j] < 0) map[i][j] = 0
            }
            visited[i][j] = true
        }
    }
}

private fun bfs(x: Int, y: Int) {
    val queue = LinkedList<Pair<Int, Int>>()
    queue.add(Pair(x, y))
    visited[x][y] = true

    while (queue.isNotEmpty()) {
        val (dx, dy) = queue.removeFirst()
        for (i in 0 until 4) {
            val nx = dx + dir[i][0]
            val ny = dy + dir[i][1]
            if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue
            if (map[nx][ny] == 0 || visited[nx][ny]) continue
            visited[nx][ny] = true
            queue.add(Pair(nx, ny))
        }
    }
}