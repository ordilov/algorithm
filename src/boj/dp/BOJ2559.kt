package boj.dp

import java.lang.Integer.max

//[수열] https://www.acmicpc.net/problem/2559
fun main() {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }
    val map = readLine()!!.split(" ").map { it.toInt() }
    val sum = IntArray(n - k + 1)
    var max = 0
    for (i in 0 .. n - k) {
        for (j in i until  i + k) {
            sum[i] += map[j]
        }
        max = max(sum[i], max)
    }
    println(sum.maxOrNull())
}