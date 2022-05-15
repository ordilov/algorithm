package boj.math

//[대칭 차집합] https://www.acmicpc.net/problem/1269
fun main() {
    readLine()
    val a = readLine()!!.split(" ").map { it.toInt() }.toSet()
    val b = readLine()!!.split(" ").map { it.toInt() }.toSet()
    println(a.subtract(b).size + b.subtract(a).size)
}