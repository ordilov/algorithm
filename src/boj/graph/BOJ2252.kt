package boj.graph

import java.util.*

// [줄 세우기] https://www.acmicpc.net/problem/2252
fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val heights = Array(n + 1) { mutableSetOf<Int>() }
    val start = IntArray(n + 1) { 0 }
    repeat(m) {
        val (a, b) = readLine()!!.split(" ").map { it.toInt() }
        heights[a].add(b)
        start[b]++
    }

    val queue = LinkedList<Int>()
    for (i in 1..n) {
        if (start[i] == 0) queue.add(i)
    }

    val str = StringBuilder()
    while (queue.isNotEmpty()) {
        val cur = queue.poll()
        str.append(cur).append(" ")

        for(height in heights[cur]) {
            start[height]--
            if (start[height] == 0) queue.add(height)
        }
    }

    println(str)
}