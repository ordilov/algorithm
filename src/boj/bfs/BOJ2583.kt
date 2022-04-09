package boj.bfs

// [영역 구하기] https://www.acmicpc.net/problem/2583
private lateinit var map: Array<BooleanArray>
private lateinit var visited: Array<BooleanArray>
private val dir = arrayOf(Pair(0, 1), Pair(0, -1), Pair(1, 0), Pair(-1, 0))
fun main() {
    val (m, n, k) = readLine()!!.split(" ").map { it.toInt() }
    val list = mutableListOf<Int>()
    map = Array(m) { BooleanArray(n) { false } }
    visited = Array(m) { BooleanArray(n) { false } }
    for (i in 0 until k) {
        val (x, y, a, b) = readLine()!!.split(" ").map { it.toInt() }
        for (j in y until b) {
            for (l in x until a) {
                map[j][l] = true
            }
        }
    }
    for (i in 0 until m) {
        for (j in 0 until n) {
            if (map[i][j] || visited[i][j]) continue
            list.add(bfs(i, j))
        }
    }
    println(list.size)
    list.sort()
    list.forEach{ print("$it ") }
}

private fun bfs(y: Int, x: Int): Int {
    visited[y][x] = true
    val queue = ArrayDeque<Pair<Int, Int>>()
    queue.add(Pair(y, x))
    var count = 1
    while (queue.isNotEmpty()) {
        val (y, x) = queue.removeFirst()
        for (i in 0 until 4) {
            val ny = y + dir[i].first
            val nx = x + dir[i].second
            if (ny < 0 || ny > map.size - 1 || nx < 0 || nx > map[0].size - 1 || map[ny][nx] || visited[ny][nx]) continue
            visited[ny][nx] = true
            count++
            queue.add(Pair(ny, nx))
        }
    }
    return count
}