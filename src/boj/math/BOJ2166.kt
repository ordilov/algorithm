package boj.math

import kotlin.math.abs

// [다각형의 면적] https://www.acmicpc.net/problem/2166
fun main() {
    val n = readLine()!!.toInt()
    val points = Array(n) {
        readLine()!!.split(" ").map { it.toLong() }.toLongArray()
    }
    var sumA = 0L
    var sumB = 0L

    for (i in 0 until n) {
        val a = points[i]
        val b = points[(i + 1) % n]
        sumA += a[0] * b[1]
        sumB += b[0] * a[1]
    }
    val area = String.format("%.1f",abs(sumA - sumB) / 2.0)
    println(area)
}