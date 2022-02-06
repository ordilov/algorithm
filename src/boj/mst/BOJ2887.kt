package boj.mst

import java.util.*

//[행성 터널] https://www.acmicpc.net/problem/2887
var parent: IntArray = intArrayOf()
var rank: IntArray = intArrayOf()

fun main() {
    val n = readLine()!!.toInt()
    val xQueue: MutableList<Pair<Int, Int>> = mutableListOf()
    val yQueue: MutableList<Pair<Int, Int>> = mutableListOf()
    val zQueue: MutableList<Pair<Int, Int>> = mutableListOf()

    repeat(n) {
        val (x, y, z) = readLine()!!.split(" ").map { it.toInt() }
        xQueue.add(Pair(it, x))
        yQueue.add(Pair(it, y))
        zQueue.add(Pair(it, z))
    }
    xQueue.sortBy { it.second }
    yQueue.sortBy { it.second }
    zQueue.sortBy { it.second }

    val queue: PriorityQueue<Pair<Pair<Int, Int>, Int>> = PriorityQueue(compareBy { it.second })
    for (i in 0 until n - 1) {
        queue.add(
            Pair(
                Pair(xQueue[i].first, xQueue[i + 1].first),
                xQueue[i + 1].second - xQueue[i].second
            )
        )
        queue.add(
            Pair(
                Pair(yQueue[i].first, yQueue[i + 1].first),
                yQueue[i + 1].second - yQueue[i].second
            )
        )
        queue.add(
            Pair(
                Pair(zQueue[i].first, zQueue[i + 1].first),
                zQueue[i + 1].second - zQueue[i].second
            )
        )
    }

    var result = 0
    parent = IntArray(n) { it }
    rank = IntArray(n) { 1 }
    while (queue.isNotEmpty()) {
        val (loc, distance) = queue.poll()
        val (a, b) = loc
        val connect = union(a, b)
        if (connect) result += distance
    }
    println(result)
}

private fun union(a: Int, b: Int): Boolean {
    val aRoot = find(a)
    val bRoot = find(b)
    if (aRoot == bRoot) return false
    if (rank[aRoot] < rank[bRoot]) {
        parent[aRoot] = bRoot
    } else {
        parent[bRoot] = aRoot
        if (rank[aRoot] == rank[bRoot]) rank[aRoot]++
    }
    return true
}

private fun find(a: Int): Int {
    if (parent[a] == a) return a
    val findParent = find(parent[a])
    parent[a] = findParent
    return findParent
}