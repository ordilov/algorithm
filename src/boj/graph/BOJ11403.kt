package boj.graph

// [경로 찾기] https://www.acmicpc.net/problem/11403
var graph = arrayOf<MutableList<Int>>()
var movable = arrayOf<IntArray>()
fun main() {
    val n = readLine()!!.toInt()
    graph = Array(n) { mutableListOf() }
    movable = Array(n) { IntArray(n) { 0 } }
    repeat(n) { i ->
        val map = readLine()!!.split(" ").map { it.toInt() }
        map.indices.forEach { j ->
            if (map[j] == 0) return@forEach
            graph[i].add(j)
        }
    }

    for (i in movable.indices) {
        for (j in movable[i].indices) {
            val visited = BooleanArray(n)
            movable[i][j] = dfs(visited, i, j)
        }
    }

    val sb = StringBuilder()
    movable.forEach {
        it.forEach {
            sb.append("${it} ")
        }
        sb.append("\n")
    }
    println(sb)
}

private fun dfs(visited: BooleanArray, now: Int, end: Int): Int {
    if (visited[now]) return 0
    visited[now] = true

    graph[now].forEach {
        if (it == end) return 1
        if (dfs(visited, it, end) == 1) return 1
    }
    return 0
}
