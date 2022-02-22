package boj.bruteforce

import kotlin.math.abs

// [리모컨] https://www.acmicpc.net/problem/1107
fun main() {
    val n = readLine()!!.toInt()
    val m = readLine()!!.toInt()
    val broken = BooleanArray(10)
    if (m != 0)
        readLine()!!.split(" ").forEach { broken[it.toInt()] = true }
    val justMove = abs(100 - n)

    var min = 987654321;
    for (i in 0 until 1000000) {
        val str = i.toString()
        var movable = true
        for (element in str) {
            if (broken[element.digitToInt()]) {
                movable = false
                break
            }
        }
        if (!movable) continue
        min = min.coerceAtMost(str.length + abs(i - n))
    }
    println(justMove.coerceAtMost(min))
}