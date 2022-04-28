package boj.bfs

import java.util.LinkedList

//[양] https://www.acmicpc.net/problem/3184
fun main() {
    val (r, c) = readLine()!!.split(" ").map { it.toInt() }
    val map = Array(r) { readLine()!!.toCharArray() }
    val visited = Array(r) { BooleanArray(c) }
    val dir = arrayOf(
        Pair(0, 1), Pair(0, -1), Pair(1, 0), Pair(-1, 0)
    )
    var lambs = 0
    var wolves = 0

    for (i in 0 until r) {
        for (j in 0 until c) {
            if (map[i][j] == '#' || visited[i][j]) continue
            val q = LinkedList<Pair<Int, Int>>()
            q.offer(Pair(i, j))
            visited[i][j] = true
            var lamb = 0
            var wolf = 0
            while(q.isNotEmpty()){
                val (y, x) = q.poll()
                if(map[y][x] == 'o') lamb++
                if(map[y][x] == 'v') wolf++
                for(k in 0 until 4) {
                    val ny = y + dir[k].first
                    val nx = x + dir[k].second
                    if(ny < 0 || nx < 0 || ny >= r || nx >= c || visited[ny][nx] || map[y][x] == '#') continue
                    visited[ny][nx] = true
                    q.offer(Pair(ny, nx))
                }
            }
            if(lamb > wolf) lambs += lamb
            else wolves += wolf
        }
    }
    println("$lambs $wolves")
}