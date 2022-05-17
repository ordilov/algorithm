package boj.divide

import kotlin.math.abs

//[용액] https://www.acmicpc.net/problem/2467
fun main() {
    readLine()
    val liquids = readLine()!!.split(" ").map { it.toInt() }.toIntArray().sorted()
    var left = 0
    var right = liquids.size - 1
    var l = left
    var r = right
    var min = Int.MAX_VALUE
    while (left < right) {
        val sum = liquids[left] + liquids[right]
        if (abs(sum) < min) {
            min = abs(sum)
            l = left
            r = right
        }
        if (sum < 0) {
            left++
        } else {
            right--
        }
    }
    println("${liquids[l]} ${liquids[r]}")
}