package boj.dfs

// [알파벳] https://www.acmicpc.net/problem/1987
private var r = 0
private var c = 0
private var answer = 0
private val dx = intArrayOf(0, 0, 1, -1)
private val dy = intArrayOf(1, -1, 0, 0)
private lateinit var map: Array<CharArray>
fun main() {
    readLine()!!.split(" ").map { it.toInt() }.let {
        r = it[0]
        c = it[1]
    }
    map = Array(r) { CharArray(c) }
    repeat(r) { index ->
        readLine()!!.let {
            map[index] = it.toCharArray()
        }
    }

    dfs(0, 0, BooleanArray(26), Array(r) { BooleanArray(c) })
    println(answer)
}

private fun dfs(
    y: Int,
    x: Int,
    checked: BooleanArray,
    visited: Array<BooleanArray>,
    count: Int = 1
) {
    checked[map[y][x] - 'A'] = true
    visited[y][x] = true

    var last = true
    for (i in dx.indices) {
        val ny = y + dy[i]
        val nx = x + dx[i]
        if (ny >= r || ny < 0 || nx >= c || nx < 0) continue
        if (visited[ny][nx]) continue
        if (checked[map[ny][nx] - 'A']) continue
        last = false
        dfs(ny, nx, checked, visited, count + 1)
        checked[map[ny][nx] - 'A'] = false
        visited[ny][nx] = false
    }
    if (last) {
        answer = maxOf(answer, count)
    }
}
