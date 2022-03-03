package boj.math

// [카잉 달력] https://www.acmicpc.net/problem/6064
fun main() {
    val t = readLine()!!.toInt()
    repeat(t) {
        val (m, n, x, y) = readLine()!!.split(" ").map { it.toInt() }
        var ny = x % n
        if(ny == 0) ny = n
        var count = x

        var find = false
        val visited = BooleanArray(n + 1)
        while (true) {
            if (ny == y) {
                find = true
                break
            }
            if (visited[ny]) break
            visited[ny] = true
            ny = (ny + m) % n
            if (ny == 0) ny = n
            count += m
        }
        println(if (find) count else -1)
    }
}