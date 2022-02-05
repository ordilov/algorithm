package boj.tree

import java.util.*

//[다리 만들기 2] https://www.acmicpc.net/problem/17472
private val dx = intArrayOf(1, 0, -1, 0)
private val dy = intArrayOf(0, 1, 0, -1)
private var visited: Array<BooleanArray> = emptyArray()
private var map: Array<BooleanArray> = emptyArray()
private var lands: Array<IntArray> = emptyArray()
private var distances: Array<IntArray> = emptyArray()
private var parent: IntArray = intArrayOf()

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    map = Array(n) { BooleanArray(m) }
    visited = Array(n) { BooleanArray(m) }
    lands = Array(n) { IntArray(m) }
    repeat(n) {
        map[it] = readLine()!!.split(" ").map { it == "1" }.toBooleanArray()
    }

    var index = 1
    for (i in 0 until n) {
        for (j in 0 until m) {
            if (map[i][j] && !visited[i][j]) {
                dfs(i, j, index++)
            }
        }
    }

    parent = IntArray(index) { it }
    distances = Array(index) { IntArray(index) { Int.MAX_VALUE } }
    for (i in 0 until n) {
        for (j in 0 until m) {
            if (!map[i][j]) continue
            for (k in 0 until 4) {
                updateDistance(i, j, n, m, k, lands[i][j])
            }
        }
    }

    val queue = PriorityQueue<Pair<Pair<Int, Int>, Int>>(compareBy { it.second })
    for (i in 0 until index) {
        for (j in i + 1 until index) {
            if (distances[i][j] == Int.MAX_VALUE) continue
            queue.add(Pair(Pair(i, j), distances[i][j]))
        }
    }
    var result = 0
    while (queue.isNotEmpty()) {
        val q = queue.poll()
        val (a, b) = q.first
        val distance = q.second
        if (union(a, b)) {
            result += distance
        }
    }
    val one = find(1)
    for (i in 2 until index) {
        parent[i] = find(i)
        if (one != parent[i]) {
            result = -1
            break
        }
    }
    println(result)
}

private fun union(x: Int, y: Int): Boolean {
    val a = find(x)
    val b = find(y)
    if (a == b) return false
    parent[a] = b
    return true
}

private fun find(x: Int): Int {
    if (parent[x] == x) return x
    return find(parent[x])
}

private fun updateDistance(x: Int, y: Int, n: Int, m: Int, type: Int, index: Int) {
    var distance = -1
    var i = x
    var j = y
    while (true) {
        i += dx[type]
        j += dy[type]
        distance++
        if (i < 0 || i >= n || j < 0 || j >= m) return
        val meet = lands[i][j]
        if (meet == 0) continue
        if (meet == index || distance <= 1) return
        val min = distances[index][meet].coerceAtMost(distance)
        distances[index][meet] = min
        distances[meet][index] = min
        return
    }
}

private fun dfs(x: Int, y: Int, cnt: Int) {
    lands[x][y] = cnt
    visited[x][y] = true
    for (i in 0 until 4) {
        val nx = x + dx[i]
        val ny = y + dy[i]
        if (nx < 0 || nx >= map.size || ny < 0 || ny >= map[0].size) continue
        if (!map[nx][ny] || visited[nx][ny]) continue
        dfs(nx, ny, cnt)
    }
}