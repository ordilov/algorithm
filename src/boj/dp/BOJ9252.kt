package boj.dp

import java.util.*

// [LCS 2] https://www.acmicpc.net/problem/9252
private lateinit var dp: Array<IntArray>
private var sb = StringBuilder()

fun main() {
    val str1 = readLine()!!
    val str2 = readLine()!!
    val n1 = str1.length
    val n2 = str2.length
    dp = Array(n1 + 1) { IntArray(n2 + 1) }
    for (i in 1 until n1 + 1) {
        for (j in 1 until n2 + 1) {
            if (str1[i - 1] == str2[j - 1]) {
                dp[i][j] = dp[i - 1][j - 1] + 1
            } else {
                dp[i][j] = dp[i - 1][j].coerceAtLeast(dp[i][j - 1])
            }
        }
    }
    sb.append("${dp[n1][n2]}\n")
    val st = Stack<Char>()
    var i = n1
    var j = n2
    while (i > 0 && j > 0) {
        if (dp[i][j] == dp[i - 1][j]) {
            i--
        } else if (dp[i][j] == dp[i][j - 1]) {
            j--
        } else {
            st.push(str1[i - 1])
            i--
            j--
        }
    }
    while (!st.isEmpty()) {
        sb.append(st.pop())
    }

    println(sb.toString())
}
