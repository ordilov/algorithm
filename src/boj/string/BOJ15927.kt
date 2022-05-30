package boj.string

//[회문은 회문아니야!!] https://www.acmicpc.net/problem/15927
fun main() {
    val s = readLine()!!
    var a = false
    for (i in 0 until s.length / 2) {
        if (s[i] != s[s.length - i - 1]) {
            println(s.length)
            return
        } else if (s[i] != s[i + 1]) a = true
    }
    println(if (a) s.length - 1 else -1)
}