package boj.dp

// [구간 합 구하기 5] https://www.acmicpc.net/problem/11660
fun main(){
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val map = Array(n+1){ IntArray(n+1) }
    repeat(n){
        val l = readLine()!!.split(" ").map { it.toInt() }.toMutableList();
        l.add(0, 0);
        map[it+1] = l.toIntArray()
    }
    val dp = Array(n+1){ IntArray(n+1) }
    for(i in 1 .. n){
        for(j in 1 .. n){
            dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + map[i][j]
        }
    }

    repeat(m) {
        val (x1, y1, x2, y2) = readLine()!!.split(" ").map { it.toInt() }
        println(dp[x2][y2] - dp[x1-1][y2] - dp[x2][y1-1] + dp[x1-1][y1-1])
    }
}