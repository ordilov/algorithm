package boj.string

//[잠수함식별] https://www.acmicpc.net/problem/2671
fun main() {
    val s = readLine()!!
    val pattern = Regex("(100+1+|01)+")
    println(if (pattern.matches(s)) "SUBMARINE" else "NOISE")
}