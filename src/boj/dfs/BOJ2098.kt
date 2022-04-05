package boj.dfs

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.min


// [외판원 순회] https://www.acmicpc.net/problem/2098

private var N: Int = 0
private lateinit var w: Array<IntArray>
private lateinit var dp: Array<IntArray>
private const val INF = 1_000_000_000

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))

    N = br.readLine().toInt()
    dp = Array(N) { IntArray((1 shl N) - 1) { INF } }
    w = Array(N) { IntArray(N) }
    repeat(N) {
        w[it] = br.readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    bw.write("${dfs(0, 1)} \n")
    bw.flush()
}


private fun dfs(node: Int, visit: Int): Int {
    if (visit == (1 shl N) - 1) {
        return if (w[node][0] == 0) INF else w[node][0]
    }
    if (dp[node][visit] != INF) {
        return dp[node][visit]
    }
    for (i in 0 until N) {
        if (visit and (1 shl i) == 0 && w[node][i] != 0) {
            dp[node][visit] = min(dp[node][visit], dfs(i, visit or (1 shl i)) + w[node][i])
        }
    }
    return dp[node][visit]
}