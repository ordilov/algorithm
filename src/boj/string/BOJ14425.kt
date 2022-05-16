package boj.string

//[문자열 집합] https://www.acmicpc.net/problem/14425
fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val words = mutableSetOf<String>()
    repeat(n){
        words.add(readLine()!!)
    }
    var count = 0
    repeat(m) {
        if (words.contains(readLine()!!)) count++
    }
    println(count)
}