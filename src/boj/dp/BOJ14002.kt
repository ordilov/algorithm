package boj.dp

// [가장 긴 증가하는 부분 수열 4] https://www.acmicpc.net/problem/14002
fun main(){
    val n = readLine()!!.toInt()
    val arr = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    val dp = IntArray(n){1}
    var sequence = mutableListOf<MutableList<Int>>()
    var max = 1
    var maxIndex = 0
    for(i in 0 until n) {
        var seq = mutableListOf<Int>()
        for(j in 0 until i) {
            if(arr[i] <= arr[j]) continue
            if(dp[i] > dp[j] + 1) continue
            dp[i] = maxOf(dp[i], dp[j] + 1)
            seq = sequence[j].toMutableList()
        }
        seq.add(arr[i])
        sequence.add(seq)
        if(dp[i] < max) continue
        max = dp[i]
        maxIndex = i
    }
    println(max)
    println(sequence[maxIndex].joinToString(" "))
}