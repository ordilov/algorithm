package boj.bfs

//[빗물] https://www.acmicpc.net/problem/14719
fun main() {
    val (h, w) = readLine()!!.split(" ").map { it.toInt() }
    val heights = readLine()!!.split(" ").map { it.toInt() }
    val map = Array(h) { Array(w) { 1 } }
    for (i in 0 until w) {
        for (j in 0 until heights[i]) {
            map[h - j - 1][i] = 2
        }
    }

    for (i in 0 until h) {
        for (j in 0 until w) {
            if (map[i][j] == 2) break
            map[i][j] = 0
        }

        for (j in w - 1 downTo 0) {
            if (map[i][j] == 2) break
            map[i][j] = 0
        }
    }

    var count = 0
    for (i in 0 until h) {
        for (j in 0 until w) {
            if (map[i][j] == 1) count++
        }
    }

    println(count)
}