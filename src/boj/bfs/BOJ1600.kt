package boj.bfs

import java.util.LinkedList

//[말이 되고픈 원숭이] https://www.acmicpc.net/problem/1600
private var k = 0
private var w = 0
private var h = 0
private var min = Int.MAX_VALUE
private lateinit var map: Array<BooleanArray>
private lateinit var visited: Array<Array<BooleanArray>>
private val dir = arrayOf(
    Pair(0, 1),
    Pair(0, -1),
    Pair(1, 0),
    Pair(-1, 0)
)

private val horses = arrayOf(
    Pair(2, -1),
    Pair(2, 1),
    Pair(1, -2),
    Pair(1, 2),
    Pair(-1, 2),
    Pair(-1, -2),
    Pair(-2, -1),
    Pair(-2, 1)
)

fun main() {
    k = readLine()!!.toInt()
    readLine()!!.split(" ").map { it.toInt() }.let {
        w = it[0]
        h = it[1]
    }
    map = Array(h) { readLine()!!.split(" ").map { it.toInt() == 1 }.toBooleanArray() }
    visited = Array(h) { Array(w) { BooleanArray(k + 1) } }
    min = bfs(0, 0)
    println(if (min == Int.MAX_VALUE) -1 else min )
}

private fun bfs(x: Int, y: Int): Int {
    val q = LinkedList<Node1600>()
    q.offer(Node1600(x, y, 0, k))
    visited[x][y][k] = true
    while (q.isNotEmpty()) {
        val (cx, cy, cc, ck) = q.poll()
        if (cx == h - 1 && cy == w - 1) return cc;

        for (i in 0 until 4) {
            val nx = cx + dir[i].first
            val ny = cy + dir[i].second
            if (nx < 0 || ny < 0 || nx >= h || ny >= w || visited[nx][ny][ck] || map[nx][ny]) continue
            visited[nx][ny][ck] = true
            q.offer(Node1600(nx, ny, cc + 1, ck))
        }

        if(ck <= 0) continue
        for (i in 0 until 8) {
            val nx = cx + horses[i].first
            val ny = cy + horses[i].second
            if (nx < 0 || ny < 0 || nx >= h || ny >= w || visited[nx][ny][ck - 1] || map[nx][ny]) continue
            visited[nx][ny][ck - 1] = true
            q.offer(Node1600(nx, ny, cc + 1, ck - 1))
        }
    }
    return min
}

private data class Node1600(val x: Int, val y: Int, val count: Int, val k: Int);