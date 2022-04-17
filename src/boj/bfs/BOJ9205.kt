package boj.bfs

import kotlin.math.abs

// [맥주 마시면서 걸어가기] https://www.acmicpc.net/problem/9205
private var c = mutableListOf<Pair<Int, Int>>()
private var rx = 0
private var ry = 0
private var n = 0
fun main() {
    val t = readLine()!!.toInt()
    repeat(t) {
        c.clear()
        n = readLine()!!.toInt()
        val (hx, hy) = readLine()!!.split(" ").map { it.toInt() }
        repeat(n) {
            c.add(readLine()!!.split(" ").map { it.toInt() }.let { (x, y) -> x to y })
        }
        readLine()!!.split(" ").map { it.toInt() }.let {
            rx = it[0]
            ry = it[1]
        }
        c.add(rx to ry)
        bfs(hx, hy)
    }
}

private fun bfs(hx: Int, hy: Int) {
    val queue = ArrayDeque<Pair<Int, Int>>()
    queue.add(hx to hy)
    val visited = BooleanArray(n + 1)
    while (queue.isNotEmpty()) {
        val (x, y) = queue.removeFirst()
        if (x == rx && y == ry) {
            println("happy")
            return
        }
        for (i in 0..n) {
            if (visited[i]) continue
            val distance = distance(x, y, c[i].first, c[i].second)
            if (distance > 1000) continue
            visited[i] = true
            queue.add(c[i].first to c[i].second)
        }
    }
    println("sad")
}

private fun distance(sx: Int, sy: Int, ex: Int, ey: Int): Int {
    return abs(sx - ex) + abs(sy - ey)
}