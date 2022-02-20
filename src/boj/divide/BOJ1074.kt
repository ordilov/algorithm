package boj.divide

import kotlin.math.pow

// [Z] https://www.acmicpc.net/problem/1074

var time = 0;
fun main() {
    val (n, r, c) = readLine()!!.split(" ").map { it.toInt() }
    val size = 2.0.pow(n.toDouble()).toInt()
    divide(size, r, c)
    println(time)
}

private fun divide(n: Int, r: Int, c: Int) {
    if (n == 0) {
        return
    }

    val square = (n / 2) * (n / 2)
    if (c >= n / 2) {
        time += square
    }
    if (r >= n / 2) {
        time += square * 2
    }

    val nr = if (r >= n / 2) (r - n / 2) else r
    val nc = if (c >= n / 2) (c - n / 2) else c
    divide(n / 2, nr, nc)
}
