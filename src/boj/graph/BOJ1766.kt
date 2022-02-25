package boj.graph

// [문제집] https://www.acmicpc.net/problem/1766
fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val graph = Array(n) { mutableListOf<Int>() }
    val pre = Array(n) { 0 }

    for (i in 0 until m) {
        val (a, b) = readLine()!!.split(" ").map { it.toInt() }
        graph[a - 1].add(b - 1)
        pre[b - 1]++
    }

    var inComplete = true
    while (inComplete) {
        inComplete = false
        for (i in 0 until n) {
            if (pre[i] != 0) continue
            print("${i + 1} ")
            inComplete = true
            pre[i] = -1
            graph[i].forEach { pre[it]-- }
            break
        }
    }
}

