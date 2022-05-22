package boj.string

//[A와 B] https://www.acmicpc.net/problem/12904
fun main() {
    val s = readLine()!!
    val t = StringBuilder(readLine()!!)

    while (s.length < t.length) {
        val last = t.length - 1
        val reverse = t[last] == 'B'
        t.deleteCharAt(last)
        if(reverse) t.reverse()
    }
    println(if (s == t.toString()) 1 else 0)
}