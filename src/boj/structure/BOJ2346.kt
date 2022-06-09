package boj.structure

//[풍선 터뜨리기] https://www.acmicpc.net/problem/2346
fun main() {
    val n = readLine()!!.toInt()
    val arr = readLine()!!.split(" ").map { it.toInt() }
    val visited = BooleanArray(n)
    val sb = StringBuilder()
    var idx = 0
    var cycle = n - 1

    while (true) {
        var move = arr[idx]
        visited[idx] = true
        sb.append("${idx + 1} ")
        if (cycle-- == 0) break

        val dir = if (move < 0) -1 else 1
        while (move != 0) {
            idx = (idx + n + dir) % n
            if (visited[idx]) continue
            move += -dir
        }
    }
    println(sb)
}