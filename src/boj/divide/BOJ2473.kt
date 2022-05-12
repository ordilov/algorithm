package boj.divide

import kotlin.math.abs

// [세 용액] https://www.acmicpc.net/problem/2473
fun main() {
    val n = readLine()!!.toInt()
    val liquids = readLine()!!.split(" ").map { it.toLong() }.sorted()
    var diff = Long.MAX_VALUE
    val res = LongArray(3)

    for (i in 0 until n) {
        var left = i + 1
        var right = n - 1

        while (left < right) {
            val sum = liquids[i] + liquids[left] + liquids[right]

            val curDiff = abs(sum)
            if (curDiff < diff) {
                diff = curDiff
                res[0] = liquids[i]
                res[1] = liquids[left]
                res[2] = liquids[right]
            }
            if (sum > 0) {
                right--
            } else {
                left++
            }
        }
    }
    println("${res[0]} ${res[1]} ${res[2]}")
}