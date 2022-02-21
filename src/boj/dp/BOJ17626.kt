package boj.dp

import kotlin.math.sqrt

// [Four Squares] https://www.acmicpc.net/problem/17626
fun main() {
    val n = readLine()!!.toInt()
    val arr = IntArray(n + 1)
    arr[1] = 1

    for (i in 2..n) {
        var min = Int.MAX_VALUE
        for (j in 1..sqrt(i.toDouble()).toInt()) {
            val temp = i - j * j
            min = min.coerceAtMost(arr[temp]);
        }

        arr[i] = min + 1;
    }

    println(arr[n])
}