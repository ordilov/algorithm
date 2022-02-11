package boj.dp

// [스티커] https://www.acmicpc.net/problem/9465
fun main() {
    val t = readLine()!!.toInt()
    repeat(t) {
        val n = readLine()!!.toInt()
        val arr = Array(2) { IntArray(n) }
        arr[0] = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
        arr[1] = readLine()!!.split(" ").map { it.toInt() }.toIntArray()

        val dp = Array(4) { IntArray(n) }
        for (i in 0 until n) {
            if (i >= 1) {
                val up = dp[1][i - 1].coerceAtLeast(dp[2][i - 1]).coerceAtLeast(dp[3][i - 1])
                val down = dp[3][i - 1].coerceAtLeast(dp[0][i - 1]).coerceAtLeast(dp[1][i - 1])
                dp[0][i] = up
                dp[1][i] = up.coerceAtLeast(dp[0][i])
                dp[2][i] = down
                dp[3][i] = down.coerceAtLeast(dp[2][i])
            }
            dp[0][i] += arr[0][i]
            dp[2][i] += arr[1][i]
        }
        var max = 0
        for (i in 0 until 4) {
            max = max.coerceAtLeast(dp[i][n-1])
        }
        println(max)
    }
}