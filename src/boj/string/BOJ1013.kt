package boj.string

//[Contact] https://www.acmicpc.net/problem/1013
fun main() {
    val t = readLine()!!.toInt()
    val regex = Regex("(100+1+|01)+")
    repeat(t) {
        val s = readLine()!!
        println(if (regex.matches(s)) "YES" else "NO")
    }
}