package boj.mst

import java.util.*
import kotlin.math.sqrt

// [우주신과의 교감] https://www.acmicpc.net/problem/1774
fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val gods = Array(n) { Pair(0.0, 0.0) }
    val routes = Array(n) { Array(n) { 0.0 } }
    val visited = Array(n) { false }
    val min = Array(n) { Double.MAX_VALUE }
    val queue = PriorityQueue<Pair<Double, Int>>(compareBy { it.first })

    repeat(n) {
        val (x, y) = readLine()!!.split(" ").map { it.toDouble() }
        gods[it] = Pair(x, y)
    }

    repeat(n) { i ->
        for (j in i + 1 until n) {
            if (j == i) continue
            val distance = getDistance(gods[i], gods[j])
            routes[i][j] = distance
            routes[j][i] = distance
        }
    }

    repeat(m) {
        val (a, b) = readLine()!!.split(" ").map { it.toInt() }
        routes[a - 1][b - 1] = 0.0
        routes[b - 1][a - 1] = 0.0
    }

    var result = 0.0
    queue.add(Pair(0.0, 0))
    repeat(n) {
        var now = queue.poll()
        while(visited[now.second]){
            now = queue.poll()
        }
        result += now.first
        visited[now.second] = true
        for (i in 0 until n) {
            if(visited[i] || min[i] <= routes[now.second][i]) continue
            min[i] = routes[now.second][i]
            queue.add(Pair(min[i], i))
        }
    }

    println(String.format("%.2f", result))
}


private fun getDistance(a: Pair<Double, Double>, b: Pair<Double, Double>): Double {
    return sqrt((a.first - b.first) * (a.first - b.first) + (a.second - b.second) * (a.second - b.second))
}



