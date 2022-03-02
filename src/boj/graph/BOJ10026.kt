package boj.graph

// [적록색약] https://www.acmicpc.net/problem/10026

private val dy = intArrayOf(1, -1, 0, 0)
private val dx = intArrayOf(0, 0, 1, -1)

fun main() {
    val n = readLine()!!.toInt()
    val map = Array(n) { CharArray(n) }
    val visited = Array(n) { BooleanArray(n) }
    var normal = 0
    var weakness = 0
    repeat(n) {
        map[it] = readLine()!!.toCharArray()
    }

    for (i in 0 until n) {
        for (j in 0 until n) {
            if (visited[i][j]) continue
            dfs(i, j, map, visited)
            normal++
        }
    }
    visited.forEach { it.fill(false) }

    for (i in map.indices) {
        for (j in map[i].indices) {
            if (map[i][j] != 'G') continue
            map[i][j] = 'R'
        }
    }

    for (i in 0 until n) {
        for (j in 0 until n) {
            if (visited[i][j]) continue
            dfs(i, j, map, visited)
            weakness++
        }
    }

    println("$normal $weakness")
}

private fun dfs(y: Int, x: Int, map: Array<CharArray>, visited: Array<BooleanArray>) {
    visited[y][x] = true
    val now = map[y][x]
    for (i in 0..3) {
        val ny = y + dy[i]
        val nx = x + dx[i]
        if (ny < 0 || ny >= map.size || nx < 0 || nx >= map.size) continue
        if (map[ny][nx] != now || visited[ny][nx]) continue
        dfs(ny, nx, map, visited)
    }
}

