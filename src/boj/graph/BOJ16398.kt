package boj.graph

import java.util.*

//[행성 연결] https://www.acmicpc.net/problem/16398
private lateinit var parent: IntArray
private val queue = PriorityQueue<Planet>()
fun main() {
    val n = readLine()!!.toInt()
    for (i in 0 until n) {
        val arr = readLine()!!.split(" ").map { it.toInt() }
        for (j in 0 until n) {
            if (arr[j] == 0) continue
            queue.add(Planet(i, j, arr[j]))
        }
    }
    parent = IntArray(n + 1)
    for (i in 0 until n + 1) {
        parent[i] = i
    }
    var answer = 0L
    while (queue.isNotEmpty()) {
        val cur = queue.poll()
        val px = find(cur.start)
        val py = find(cur.end)
        if (px == py) continue
        union(px, py)
        answer += cur.cost
    }
    println(answer)
}

private fun find(node: Int): Int {
    if (parent[node] == node) {
        return node
    }
    parent[node] = find(parent[node])
    return parent[node]
}

private fun union(a: Int, b: Int) {
    val pa = find(a)
    val pb = find(b)

    if (pa > pb) {
        parent[pb] = pa
    } else {
        parent[pa] = pb
    }
}

private data class Planet(val start: Int, val end: Int, val cost: Int) : Comparable<Planet> {
    override fun compareTo(other: Planet): Int {
        return cost - other.cost
    }

}