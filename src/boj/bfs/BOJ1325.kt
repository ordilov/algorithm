package boj.bfs

// [효율적인 해킹] https://www.acmicpc.net/problem/1325
private var N: Int = 0
private var M: Int = 0
private lateinit var map: Array<MutableList<Int>>
private lateinit var count: Array<Int>
private var max = 0
fun main() {
    readLine()!!.split(' ').let {
        N = it[0].toInt()
        M = it[1].toInt()
    }
    map = Array(N + 1) { mutableListOf() }
    count = Array(N + 1) { 0 }
    for (i in 0 until M) {
        readLine()!!.split(' ').let {
            map[it[1].toInt()].add(it[0].toInt())
        }
    }
    for (i in 1..N) {
        max = maxOf(max, bfs(i))
    }

    val sb = StringBuilder()
    for (i in 1..N) {
        if (count[i] != max) continue
        sb.append("$i ")
    }
    println(sb.toString())
}

private fun bfs(start: Int): Int {
    val queue = ArrayDeque<Int>()
    val visited = Array(N + 1) { false }
    queue.add(start)
    count[start]++
    visited[start] = true
    while (queue.isNotEmpty()) {
        val cur = queue.removeFirst()
        for (i in map[cur]) {
            if (visited[i]) continue
            visited[i] = true
            count[start]++
            queue.add(i)
        }
    }
    return count[start]
}