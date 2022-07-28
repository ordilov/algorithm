package programmers.practice

class Solution12914 {
    fun solution(n: Int): Long {
        val dp = IntArray(n + 1) { it }.also {
            for (i in 3..n) {
                it[i] = (it[i - 1] + it[i - 2]) % 1234567
            }
        }

        return dp[n].toLong()
    }
}

fun main() {
    println(Solution12914().solution(4))
}