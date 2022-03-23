package boj.dp

import java.util.*

// [숨바꼭질 2] https://www.acmicpc.net/problem/12851
private const val MAX = 100001
fun main() {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }
    val visited = BooleanArray(MAX) { false }
    var time = 0
    var count = 0
    var reached = false

    val queue = LinkedList<Pair<Int, Int>>()
    queue.add(Pair(n, 0))

    while (queue.isNotEmpty()) {
        val (x, y) = queue.poll()
        visited[x] = true

        if (time != 0 && y > time) break

        if (x == k) {
            time = y
            count++
            reached = true
        }

        if (reached) continue

        var next = x - 1
        if (next >= 0 && !visited[next]) {
            queue.add(Pair(x - 1, y + 1))
        }

        next = x + 1
        if (next < MAX && !visited[next]) {
            queue.add(Pair(x + 1, y + 1))
        }

        next = x * 2
        if (next < MAX && !visited[next]) {
            queue.add(Pair(x * 2, y + 1))
        }
    }

    println(time)
    println(count)
}