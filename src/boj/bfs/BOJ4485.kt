package boj.bfs

import java.util.PriorityQueue

//[녹색 옷 입은 애가 젤다지?] https://www.acmicpc.net/problem/4485
fun main() {
    var n = 1
    val dir = arrayOf(
        Pair(0, -1),
        Pair(0, 1),
        Pair(-1, 0),
        Pair(1, 0)
    )
    var count = 1
    val sb = StringBuilder()
    while (true) {
        n = readLine()!!.toInt()
        if (n == 0) break
        val map = Array(n) { readLine()!!.split(" ").map { it.toInt() } }
        val sum = Array(n) { IntArray(n) { Int.MAX_VALUE } }
        val queue = PriorityQueue<Node4485>(compareBy{it.sum})
        queue.add(Node4485(0, 0, 0))
        sum[0][0] = map[0][0]

        while (queue.isNotEmpty()) {
            val (y, x, s) = queue.poll()
            if(sum[y][x] < s) continue
            for (i in 0..3) {
                val ny = y + dir[i].first
                val nx = x + dir[i].second
                if (ny < 0 || nx < 0 || ny >= n || nx >= n) continue
                if (sum[ny][nx] <= sum[y][x] + map[ny][nx]) continue
                sum[ny][nx] = sum[y][x] + map[ny][nx]
                queue.offer(Node4485(ny, nx, sum[ny][nx]))
            }
        }
        sb.append("Problem ${count++}: ${sum[n - 1][n - 1]}\n")
    }
    println(sb)
}

private data class Node4485(val y: Int, val x: Int, val sum: Int)