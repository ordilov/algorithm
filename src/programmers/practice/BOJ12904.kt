package programmers.practice

class Solution12904 {
    fun solution(s: String): Int {
        for (i in s.length downTo 2) {
            for (j in 0..s.length - i) {
                if ((0 until i / 2).any { k -> s[j + k] != s[j + i - k - 1] }) {
                    return i
                }
            }
        }
        return 1
    }
}

fun main() {
    println(Solution12904().solution("abcdcba"))
    println(Solution12904().solution("abacde"))
}