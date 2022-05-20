package boj.string

//[전화번호 목록] https://www.acmicpc.net/problem/5052
fun main() {
    val t = readLine()!!.toInt()
    repeat(t) {
        val n = readLine()!!.toInt()
        val arr = Array(n) { readLine()!! }.sortedArray()
        var conflict = false
        for (i in 1 until n) {
            if (!arr[i].startsWith(arr[i - 1])) continue
            conflict = true
            break
        }
        println(if (conflict) "NO" else "YES")
    }
}
