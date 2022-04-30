package boj.bfs

import java.util.*

// [점프 점프] https://www.acmicpc.net/problem/11060
fun main() {
    val n = readLine()!!.toInt()
    val map = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    val count = IntArray(n) { Int.MAX_VALUE }
    val queue = LinkedList<Int>()
    queue.offer(0)
    count[0] = 0
    while (queue.isNotEmpty()) {
        val i = queue.poll()
        for (j in 1..map[i]) {
            if (i + j >= n) continue
            if (count[i + j] <= count[i] + 1) continue
            count[i + j] = count[i] + 1
            queue.add(i + j)
        }
    }
    if (count[n - 1] == Int.MAX_VALUE) println(-1)
    else println(count[n - 1])
}