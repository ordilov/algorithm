package boj.graph

// [케빈 베이컨의 6단게 법칙]
fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val graph = Array(n + 1) { Array(n + 1) { 101 } }
    for (i in 1..n) {
        graph[i][i] = 0
        graph[i][0] = 0
    }
    repeat(m) {
        val (a, b) = readLine()!!.split(" ").map { it.toInt() }
        graph[a][b] = 1
        graph[b][a] = 1
    }

    for (k in 1..n) {
        for (i in 1..n) {
            for (j in 1..n) {
                graph[i][j] = minOf(graph[i][j], graph[i][k] + graph[k][j])
            }
        }
    }
    var min = Int.MAX_VALUE
    var minIndex = -1
    for (i in 1..n) {
        val sum = graph[i].sum()
        if (sum >= min) continue
        min = sum
        minIndex = i
    }
    println(minIndex)


}