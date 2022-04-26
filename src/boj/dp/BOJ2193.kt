package boj.dp

//[이진수] https://www.acmicpc.net/problem/2193
fun main(){
    val n = readLine()!!.toInt()
    val dp = Array(n + 1){LongArray(2)}
    dp[1][1] = 1
    for(i in 2 .. n){
        dp[i][1] = dp[i-1][0]
        dp[i][0] = dp[i-1][0] + dp[i-1][1]
    }
    println(dp[n][0] + dp[n][1])
}