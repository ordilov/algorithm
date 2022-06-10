package boj.dp

// [1, 2, 3 더하기 9] https://www.acmicpc.net/problem/16195
private const val DIV = 1_000_000_009

fun main() {
    val t = readLine()!!.toInt()
    val sb = StringBuilder()
    val dp = Array(1001) { LongArray(1001) }
    for (i in 1..1000) {
        dp[i][i] = 1
    }
    dp[2][1] = 1
    dp[3][1] = 1
    dp[3][2] = 2
    for (i in 4..1000) {
        for (j in 2 until i) {
            dp[i][j] = (dp[i - 1][j - 1] + dp[i - 2][j - 1] + dp[i - 3][j - 1]) % 1000000009
        }
    }

    repeat(t) {
        val (n, m) = readLine()!!.split(" ").map { it.toInt() }
        var sum: Long = 0
        for (j in 1..m) {
            sum = (sum + dp[n][j]) % DIV
        }
        sb.append(sum).append("\n")
    }
    println(sb)
}