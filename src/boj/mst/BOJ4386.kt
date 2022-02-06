package boj.mst

import kotlin.math.floor
import kotlin.math.sqrt

//[별자리 만들기] https://www.acmicpc.net/problem/4386
fun main() {
    val n = readLine()!!.toInt()
    val stars: MutableList<Pair<Double, Double>> = mutableListOf()
    val lines: MutableMap<Pair<Int, Int>, Double> = mutableMapOf()
    val parent: Array<Int> = Array(n) { it }
    val rank: Array<Int> = Array(n) { 1 }
    repeat(n) {
        val (x, y) = readLine()!!.split(" ").map { it.toDouble() }
        stars.add(Pair(x, y))
    }

    repeat(n) {
        for (i in it + 1 until n) {
            lines[Pair(it, i)] = getDistance(stars[it], stars[i])
        }
    }

    val sorted = lines.toList().sortedBy { it.second }
    var result: Double = 0.0;
    sorted.forEach() {
        val (a, b) = it.first
        val distance = it.second

        if (find(parent, a) == find(parent, b)) return@forEach
        union(parent, rank, a, b)
        result += distance
    }
    println(floor(result * 100) / 100)
}


private fun union(parent: Array<Int>, rank: Array<Int>, a: Int, b: Int) {
    val rootA = find(parent, a)
    val rootB = find(parent, b)
    if (rank[rootA] < rank[rootB]) {
        parent[rootA] = rootB
    } else {
        parent[rootB] = rootA
        if (rank[rootA] == rank[rootB]) rank[rootA]++
    }
}

private fun find(parent: Array<Int>, a: Int): Int {
    var root = a
    while (parent[root] != root) {
        root = parent[root]
    }
    return root
}

private fun getDistance(star1: Pair<Double, Double>, star2: Pair<Double, Double>): Double {
    return sqrt((star1.first - star2.first) * (star1.first - star2.first) + (star1.second - star2.second) * (star1.second - star2.second))
}