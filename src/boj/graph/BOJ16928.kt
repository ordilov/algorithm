package boj.graph

import java.util.*

// [뱀과 사다리 게임] https://www.acmicpc.net/problem/16928
fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val board = Array(101) { 0 }
    val counts = Array(101) { 0 }
    repeat(n + m) {
        val (x, y) = readLine()!!.split(" ").map { it.toInt() }
        board[x] = y
    }

    val queue = LinkedList<Int>()
    queue.add(1)

    while (queue.isNotEmpty()) {
        val now = queue.poll()
        if (now == 100) {
            println(counts[100])
            return
        }

        for (i in now + 1..now + 6) {
            if (i > 100) break
            if (counts[i] != 0) continue
            if (board[i] != 0) {
                if (counts[board[i]] != 0) continue
                counts[board[i]] = counts[now] + 1
                queue.add(board[i])
            } else {
                counts[i] = counts[now] + 1
                queue.add(i)
            }

        }
    }
}