package boj.bfs

import java.util.*

// [물통] https://www.acmicpc.net/problem/2251
fun main() {
    val (A, B, C) = readLine()!!.split(" ").map { it.toInt() }
    val arr = Array(B + 1) { BooleanArray(C + 1) }
    arr[0][C] = true
    val q = LinkedList<Pair<Int, Int>>()
    val l = TreeSet<Int>()
    q.add(Pair(0, C))
    while (q.isNotEmpty()) {
        val (b, c) = q.poll()
        val a = C - (b + c)
        if (a == 0) {
            l.add(c)
        }
        if (a != 0) {
            var add = a.coerceAtMost(B - b)
            if (!arr[b + add][c]) {
                arr[b + add][c] = true
                q.add(Pair(b + add, c))
            }

            add = a.coerceAtMost(C - c)
            if (!arr[b][c + add]) {
                arr[b][c + add] = true
                q.add(Pair(b, c + add))
            }
        }

        if (b != 0) {
            var add = b.coerceAtMost(A - a)
            if (!arr[b - add][c]) {
                arr[b - add][c] = true
                q.add(Pair(b - add, c))
            }
            add = b.coerceAtMost(C - c)
            if (!arr[b - add][c + add]) {
                arr[b - add][c + add] = true
                q.add(Pair(b - add, c + add))
            }
        }

        if (c != 0) {
            var add = c.coerceAtMost(A - a)
            if (!arr[b][c - add]) {
                arr[b][c - add] = true
                q.add(Pair(b, c - add))
            }
            add = c.coerceAtMost(B - b)
            if (!arr[b + add][c - add]) {
                arr[b + add][c - add] = true
                q.add(Pair(b + add, c - add))
            }
        }

    }

    l.forEach { print("$it ") }
}