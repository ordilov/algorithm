package boj.math

// [보물] https://www.acmicpc.net/problem/1026
fun main() {
    val n = readLine()!!.toInt()
    var a = readLine()!!.split(" ").map { it.toInt() }
    var b = readLine()!!.split(" ").map { it.toInt() }

    var answer = 0
    b = b.sorted()
    a = a.sortedDescending()
    for (i in 0 until n) {
        answer += a[i] * b[i]
    }
    println(answer)
}