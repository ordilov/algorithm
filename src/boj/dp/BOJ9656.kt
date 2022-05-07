package boj.dp

// [돌 게임 2] https://www.acmicpc.net/problem/9656
fun main() {
    println(if (readLine()!!.toInt() % 2 == 0) "SK" else "CY")
}