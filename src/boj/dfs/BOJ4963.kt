package boj.dfs

// [섬의 개수] https://www.acmicpc.net/problem/4963
private lateinit var visited: Array<BooleanArray>
private lateinit var map: Array<IntArray>

fun main() {
    while (true) {
        val line = readLine()!!
        if (line == "0 0") break
        val (w, h) = line.split(" ").map { it.toInt() }
        map = Array(h) { IntArray(w) }
        visited = Array(h) { BooleanArray(w) }
        for (i in 0 until h) {
            val row = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
            for (j in 0 until w) {
                map[i][j] = row[j]
            }
        }

        var count = 0
        for(i in 0 until h){
            for(j in 0 until w) {
                if(map[i][j] == 0 || visited[i][j]) continue
                dfs(i, j)
                count++
            }
        }
        println(count)
    }
}

private fun dfs(h: Int, w: Int) {
    visited[h][w] = true
    for (i in -1..1) {
        for (j in -1..1) {
            if (i == 0 && j == 0) continue
            val nw = w + j
            val nh = h + i
            if (nw < 0 || nw >= map[0].size || nh < 0 || nh >= map.size) continue
            if (map[nh][nw] == 0 || visited[nh][nw]) continue
            dfs(nh, nw)
        }
    }
}