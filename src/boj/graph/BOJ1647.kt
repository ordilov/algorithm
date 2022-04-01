package boj.graph

import java.util.*

// [도시 분할 계획] https://www.acmicpc.net/problem/1647
private var n = 0
private var m = 0
private lateinit var parent: IntArray
private val queue = PriorityQueue<Node>(compareBy { it.distance })
fun main() {
    readLine()!!.split(' ').let {
        n = it[0].toInt()
        m = it[1].toInt()
    }
    parent = IntArray(n + 1) { it }

    repeat(m) {
        readLine()!!.split(' ').map { it.toInt() }.let {
            queue.add(Node(it[0], it[1], it[2]))
        }
    }

    println(kruskal())
}

private class Node(val s: Int, val d: Int, val distance: Int)

private fun kruskal(): Int {
    var result = 0
    var count = 0
    while (count < n - 2) {
        val node = queue.poll()
        if (find(node.s) == find(node.d)) continue
        union(node.s, node.d)
        result += node.distance
        count++
    }
    return result
}

private fun find(x: Int): Int {
    if (x == parent[x]) return x
    parent[x] = find(parent[x])
    return parent[x]
}

private fun union(x: Int, y: Int) {
    val xRoot = find(x)
    val yRoot = find(y)
    if (xRoot == yRoot) return
    if (parent[xRoot] < parent[yRoot]) {
        parent[xRoot] = yRoot
    } else {
        parent[yRoot] = xRoot
    }
}