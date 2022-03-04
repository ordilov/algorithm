package boj.sweeping

// [선 긋기] https://www.acmicpc.net/problem/2170
fun main() {
    val n = readLine()!!.toInt()
    var answer = 0
    var start = -1000 * 1000 * 1000
    var end = -1000 * 1000 * 1000
    val loc = mutableListOf<Pair<Int, Int>>()
    repeat(n) {
        val (x, y) = readLine()!!.split(" ").map { it.toInt() }
        loc.add(Pair(x, y))
    }
    loc.sortBy { it.first }

    loc.forEach {
        if (it.first > end) {
            answer += end - start
            start = it.first
            end = it.second
        } else if (end in it.first until it.second) {
            end = it.second
        }
    }
    answer += end - start
    println(answer)
}