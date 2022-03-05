package boj.sweeping

import kotlin.math.max

// [수상 택시] https://www.acmicpc.net/problem/2836
fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val loc = mutableListOf<Pair<Int, Int>>()

    repeat(n) {
        val (x, y) = readLine()!!.split(" ").map { it.toInt() }
        if (y > x) return@repeat
        loc.add(Pair(y, x))
    }
    loc.sortBy { it.first }
    var s = 0
    var e = 0
    var res = m.toLong()

    loc.forEach {
        if(e < it.first){
            res += 2 * (e - s)
            s = it.first
            e = it.second
        } else {
            e = max(e, it.second)
        }
    }
    res += 2 * (e - s)
    println(res)
}