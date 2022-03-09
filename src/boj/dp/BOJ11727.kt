package boj.dp

// [2xn 타일링 2] https://www.acmicpc.net/problem/11727
fun main() {
    val n = readLine()!!.toInt()
    val dp = IntArray(n + 1)
    val divide = 10007
    dp[1] = 1
    if (n == 1) {
        println(1)
        return
    }
    dp[2] = 3
    for (i in 3..n) {
        dp[i] = (dp[i - 1] + dp[i - 2] * 2) % divide
    }
    println(dp[n])
}