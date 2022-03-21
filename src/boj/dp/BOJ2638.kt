package boj.dp

// [치즈] https://www.acmicpc.net/problem/2638

private var map = arrayOf<IntArray>()
private var visited = arrayOf<BooleanArray>()
private var cheeses = mutableListOf<Pair<Int, Int>>()
private var cheeseCount = 0

private val dir = arrayOf(
    intArrayOf(1, 0),
    intArrayOf(-1, 0),
    intArrayOf(0, 1),
    intArrayOf(0, -1)
)

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    map = Array(n) { IntArray(m) { 0 } }
    visited = Array(n) { BooleanArray(m) { false } }
    repeat(n) {
        val row = readLine()!!.split(" ").map { it.toInt() }
        row.forEachIndexed { col, i ->
            map[it][col] = i
            if (i != 1) return@forEachIndexed
            cheeseCount++
            cheeses.add(Pair(it, col))
        }
    }
    var time = 0

    while (cheeseCount > 0) {
        time++
        visited = Array(n) { BooleanArray(m) { false } }
        dfs(0, 0)
        melt()
    }

    println(time)
}

private fun dfs(y: Int, x: Int) {
    visited[y][x] = true
    map[y][x] = 2

    for (d in dir) {
        val ny = y + d[0]
        val nx = x + d[1]
        if (ny < 0 || ny >= map.size || nx < 0 || nx >= map[0].size) continue
        if (visited[ny][nx] || map[ny][nx] == 1) continue
        dfs(ny, nx)
    }
}

private fun melt() {
    val delete = mutableListOf<Pair<Int, Int>>()
    for (cheese in cheeses) {
        var count = 0
        for (k in dir) {
            val ny = cheese.first + k[0]
            val nx = cheese.second + k[1]
            if (ny < 0 || ny >= map.size || nx < 0 || nx >= map[0].size) continue
            if (map[ny][nx] == 2) count++
        }

        if (count < 2) continue
        map[cheese.first][cheese.second] = 0
        delete.add(cheese)
        cheeseCount--
    }
    cheeses.removeAll(delete)
}