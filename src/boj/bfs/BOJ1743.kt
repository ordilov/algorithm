package boj.bfs

// [음식물 피하기] https://www.acmicpc.net/problem/1743
private val dir = arrayOf(
    arrayOf(1, 0),
    arrayOf(-1, 0),
    arrayOf(0, 1),
    arrayOf(0, -1)
)

fun main() {
    val (n, m, k) = readLine()!!.split(" ").map { it.toInt() }
    val map = Array(n) { Array(m) { 0 } }
    val visited = Array(n) { Array(m) { false } }
    repeat(k) {
        val (x, y) = readLine()!!.split(" ").map { it.toInt() }
        map[x - 1][y - 1] = 1
    }

    var max = 0
    for (i in 0 until n) {
        for (j in 0 until m) {
            if (map[i][j] == 0 || visited[i][j] == true) continue
            val queue = ArrayList<Pair<Int, Int>>()
            queue.add(Pair(i, j))
            visited[i][j] = true
            var count = 1
            while (queue.isNotEmpty()) {
                val (x, y) = queue.removeFirst()
                for (k in dir) {
                    val nx = x + k[0]
                    val ny = y + k[1]
                    if (nx in 0 until n && ny in 0 until m && map[nx][ny] == 1 && visited[nx][ny] == false) {
                        queue.add(Pair(nx, ny))
                        visited[nx][ny] = true
                        count++
                    }
                }
            }
            max = maxOf(max, count)
        }
    }
    println(max)
}