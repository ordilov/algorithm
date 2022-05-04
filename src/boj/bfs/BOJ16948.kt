package boj.bfs

import java.util.*

//[데스 나이트] https://www.acmicpc.net/problem/16948
private val horses = arrayOf(
    Pair(-2, -1),
    Pair(-2, 1),
    Pair(0, -2),
    Pair(0, 2),
    Pair(2, -1),
    Pair(2, 1)
)

private lateinit var map: Array<IntArray>

fun main() {
    val n = readLine()!!.toInt()
    map = Array(n) { IntArray(n) { Int.MAX_VALUE } }
    val (r1, c1, r2, c2) = readLine()!!.split(" ").map { it.toInt() }
    bfs(r1, c1, r2, c2, n)
}

private fun bfs(r1: Int, c1: Int, r2: Int, c2: Int, n: Int) {
    val queue = LinkedList<Node16948>()
    queue.add(Node16948(r1, c1))
    map[r1][c1] = 0
    while (queue.isNotEmpty()) {
        val (y, x) = queue.poll()
        if (y == r2 && x == c2) {
            println(map[y][x])
            return
        }
        for (i in horses.indices) {
            val hy = y + horses[i].first
            val hx = x + horses[i].second
            if (hy < 0 || hx < 0 || hy >= n || hx >= n) continue
            if (map[hy][hx] <= map[y][x] + 1) continue
            map[hy][hx] = map[y][x] + 1
            queue.add(Node16948(hy, hx))
        }
    }
    println(-1)
}

private data class Node16948(val y: Int, val x: Int)