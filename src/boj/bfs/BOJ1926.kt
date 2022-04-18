package boj.bfs

import java.lang.Integer.max

// [그림] https://www.acmicpc.net/problem/1926
private var n = 0
private var m = 0
private lateinit var map: Array<BooleanArray>
private lateinit var visited: Array<BooleanArray>
private val dir = arrayOf(Pair(0, 1), Pair(0, -1), Pair(1, 0), Pair(-1, 0))
fun main(){
    readLine()!!.split(' ').let {
        n = it[0].toInt()
        m = it[1].toInt()
    }
    map = Array(n){BooleanArray(m)}
    visited = Array(n){BooleanArray(m)}
    for(i in 0 until n){
        readLine()!!.split(' ').let {
            for(j in 0 until m){
                map[i][j] = it[j].toInt() == 1
            }
        }
    }
    var max = 0
    var count = 0
    for(i in 0 until n){
        for(j in 0 until m){
            if(!map[i][j] || visited[i][j]) continue
            max = max(max, bfs(i, j))
            count++
        }
    }
    println(count)
    println(max)
}

private fun bfs(x: Int, y: Int): Int{
    val q = ArrayDeque<Pair<Int, Int>>()
    q.add(Pair(x, y))
    visited[x][y] = true
    var cnt = 1
    while(q.isNotEmpty()){
        val(i, j) = q.removeFirst()
        for(k in 0 until 4){
            val(dx, dy) = dir[k]
            val nx = i + dx
            val ny = j + dy
            if(nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny] || !map[nx][ny]) continue
            q.add(Pair(nx, ny))
            visited[nx][ny] = true
            cnt++
        }
    }
    return cnt
}