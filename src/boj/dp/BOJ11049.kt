package boj.dp

// [행렬 곱셈 순서] https://www.acmicpc.net/problem/11049
var min = Integer.MAX_VALUE

fun main() {
    val n = readLine()!!.toInt()
    val s = mutableListOf<Pair<Int, Int>>()
    repeat(n) {
        val (r, c) = readLine()!!.split(" ").map { it.toInt() }
        s.add(Pair(r, c))
    }
    val dp = Array(n) { IntArray(n) { Integer.MAX_VALUE } }
    for (i in 0 until n) {
        dp[i][i] = 0
    }

    for (k in 1 until n) {
        for (i in 0 until n - k) {
            for (j in i until i + k) {
                dp[i][i + k] = minOf(
                    dp[i][i + k],
                    dp[i][j] + dp[j + 1][i + k] + s[i].first * s[j].second * s[i + k].second
                )
            }
        }
    }

    println(dp[0][n - 1])
}
