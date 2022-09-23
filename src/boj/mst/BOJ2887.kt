package boj.mst

import java.util.*

//[행성 터널] https://www.acmicpc.net/problem/2887
private lateinit var parent: IntArray
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val xQueue = ArrayList<Pair<Int, Int>>(n)
    val yQueue = ArrayList<Pair<Int, Int>>(n)
    val zQueue = ArrayList<Pair<Int, Int>>(n)
    parent = IntArray(n) { it }

    repeat(n) {
        val (x, y, z) = readLine().split(" ").map { i -> i.toInt() }
        xQueue.add(Pair(it, x))
        yQueue.add(Pair(it, y))
        zQueue.add(Pair(it, z))
    }
    xQueue.sortBy { it.second }
    yQueue.sortBy { it.second }
    zQueue.sortBy { it.second }

    val queue = PriorityQueue<Triple<Int, Int, Int>>(compareBy { it.third })
    for (i in 0 until n - 1) {
        queue.offer(Triple(xQueue[i].first, xQueue[i + 1].first, xQueue[i + 1].second - xQueue[i].second))
        queue.offer(Triple(yQueue[i].first, yQueue[i + 1].first, yQueue[i + 1].second - yQueue[i].second))
        queue.offer(Triple(zQueue[i].first, zQueue[i + 1].first, zQueue[i + 1].second - zQueue[i].second))
    }

    var result = 0
    while (queue.isNotEmpty()) {
        val (from, to, distance) = queue.poll()
        val connect = union(from, to)
        if (connect) result += distance
    }
    println(result)
}

private fun union(a: Int, b: Int): Boolean {
    val aRoot = find(a)
    val bRoot = find(b)
    if (aRoot == bRoot) return false
    parent[bRoot] = aRoot
    return true
}

private fun find(child: Int): Int {
    if (parent[child] == child) return child
    val findParent = find(parent[child])
    parent[child] = findParent
    return findParent
}