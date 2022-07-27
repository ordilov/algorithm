package boj.dp

// [개똥벌레] https://www.acmicpc.net/problem/3020
fun main() {
    val (n, h) = readLine()!!.split(' ').map { it.toInt() }
    val down = Array(h + 1) { 0 }
    val up = Array(h + 1) { 0 }
    for (i in 0 until n / 2) {
        down[readLine()!!.toInt()]++
        up[readLine()!!.toInt()]++
    }

    for (i in h - 1 downTo 1) {
        down[i] += down[i + 1]
        up[i] += up[i + 1]
    }

    val stones = Array(h + 1) { n + 1 }
    for (i in 1..h) {
        stones[i] = down[i] + up[h - i + 1]
    }

    val min = stones.minOf { it }
    val minCount = stones.count { it == min }

    println("$min $minCount")
}