package boj.string

//[공통 부분 문자열] https://www.acmicpc.net/problem/5582
fun main() {
    val a = readLine()!!.toCharArray()
    val b = readLine()!!.toCharArray()
    val al = a.size
    val bl = b.size
    var max = 0
    val map = Array(al + 1) { IntArray(bl + 1) }
    for (i in 1..al) {
        for (j in 1..bl) {
            if (a[i - 1] != b[j - 1]) continue
            map[i][j] = map[i - 1][j - 1] + 1
            max = Math.max(max, map[i][j])
        }
    }
    println(max)
}