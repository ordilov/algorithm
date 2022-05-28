package boj.string

//[LCS 3] https://www.acmicpc.net/problem/1958
fun main() {
    val a = readLine()!!.toCharArray()
    val b = readLine()!!.toCharArray()
    val c = readLine()!!.toCharArray()

    val dp = Array(a.size + 1) { Array(b.size + 1) { IntArray(c.size + 1) } }
    for (i in 1..a.size) {
        for (j in 1..b.size) {
            for (k in 1..c.size) {
                if (a[i - 1] == b[j - 1] && b[j - 1] == c[k - 1]) {
                    dp[i][j][k] = dp[i - 1][j - 1][k - 1] + 1;
                } else {
                    dp[i][j][k] = maxOf(dp[i - 1][j][k], dp[i][j - 1][k], dp[i][j][k - 1])
                }
            }
        }
    }
    println(dp[a.size][b.size][c.size])
}