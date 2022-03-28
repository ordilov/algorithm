package boj.implementation

// [치즈] https://www.acmicpc.net/problem/2636

private lateinit var cheeses: Array<Array<Int>>
private lateinit var visited: Array<BooleanArray>
private val dx = intArrayOf(0, 0, 1, -1)
private val dy = intArrayOf(1, -1, 0, 0)
private var count = 0
private var n = 0
private var m = 0

fun main() {
    readLine()!!.split(' ').map { it.toInt() }.let {
        n = it[0]
        m = it[1]
    }
    cheeses = Array(n) { Array(m) { 0 } }
    visited = Array(n) { BooleanArray(m) { false } }
    for (i in 0 until n) {
        cheeses[i] = readLine()!!.split(' ').map { it.toInt() }.toTypedArray()
    }
    var time = 0
    while (isCheese()) {
        visited.forEach { v -> v.fill(false) }
        visited[0][0] = true
        count = 0
        dfs(0, 0)
        time++
    }
    println("$time\n$count")
}

fun isCheese(): Boolean {
    var isCheese = false
    for (i in 0 until n) {
        for (j in 0 until m) {
            if (cheeses[i][j] == 0) continue
            if (cheeses[i][j] == 2) cheeses[i][j] = 0
            if (cheeses[i][j] == 1) isCheese = true
        }
    }
    return isCheese
}

private fun dfs(x: Int, y: Int) {
    for (i in 0..3) {
        val nx: Int = x + dx[i]
        val ny: Int = y + dy[i]

        if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
            continue
        }
        if (visited[nx][ny]) continue
        visited[nx][ny] = true
        if (cheeses[nx][ny] == 1) {
            cheeses[nx][ny] = 2
            count++
        } else {
            dfs(nx, ny)
        }
    }

}
