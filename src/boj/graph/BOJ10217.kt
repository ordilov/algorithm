package boj.graph

import java.util.*

// [KCM Travel] https://www.acmicpc.net/problem/10217
fun main() {
    val sb = StringBuilder()
    val t = readLine()!!.toInt()
    repeat(t) {
        val (n, m, k) = readLine()!!.split(" ").map { it.toInt() }
        val graph = Array(n + 1) { ArrayList<Ticket>() }
        repeat(k) {
            val (u, v, c, d) = readLine()!!.split(" ").map { it.toInt() }
            graph[u].add(Ticket(v, c, d))
        }
        val queue = PriorityQueue<Ticket>()
        val dist = Array(n + 1) { Array(m + 1) { Int.MAX_VALUE } }
        dist[1][0] = 0
        queue.add(Ticket(1, 0, 0))
        var answer = Int.MAX_VALUE

        while (queue.isNotEmpty()) {
            val cur = queue.poll()
            if (cur.v == n) {
                answer = cur.d
                break
            }
            if (dist[cur.v][cur.c] < cur.d) continue
            for (ticket in graph[cur.v]) {
                val cost = cur.c + ticket.c
                val time = cur.d + ticket.d

                if (cost > m) continue
                if(dist[ticket.v][cost] > time) {
                    dist[ticket.v][cost] = time
                    queue.add(Ticket(ticket.v, cost, time))
                }
            }
        }
        sb.append(if (answer == Int.MAX_VALUE) "Poor KCM" else answer).append("\n")
    }
    println(sb)
}

private class Ticket(val v: Int, val c: Int, val d: Int) : Comparable<Ticket> {
    override fun compareTo(other: Ticket): Int = d.compareTo(other.d)
}

