package boj.dp

import java.util.*

// [숨바꼭질 3] https://www.acmicpc.net/problem/13549
fun main() {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }
    val visited = BooleanArray(100001)
    val queue = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
    queue.add(Pair(n, 0))

    while (queue.isNotEmpty()) {
        val now = queue.poll()
        val value = now.first
        val step = now.second
        if ((value == k) || (value * 2 == k)) {
            println(step)
            return
        }
        visited[value] = true
        if (value + 1 <= 100000 && !visited[value + 1])
            queue.add(Pair(value + 1, step + 1))
        if (value * 2 <= 100000 && !visited[value * 2])
            queue.add(Pair(2 * value, step))
        if (value - 1 >= 0 && !visited[value - 1])
            queue.add(Pair(value - 1, step + 1))
    }
}