package boj.bfs

// [보물섬] https://www.acmicpc.net/problem/2589
private lateinit var map: Array<List<Boolean>>
private val dir = arrayOf(Pair(1, 0), Pair(-1, 0), Pair(0, 1), Pair(0, -1))
fun main(){
    val (h, w) = readLine()!!.split(" ").map { it.toInt() }
    map = Array(h){readLine()!!.toCharArray().map { it == 'L' }}

    var max = 0
    for(i in 0 until h){
        for(j in 0 until w){
            if(!map[i][j]) continue
            max = maxOf(bfs(i,j), max)
        }
    }
    println(max)
}

private fun bfs(y: Int, x: Int): Int {
    val queue = ArrayDeque<Node>()
    val visited = Array(map.size){Array(map[0].size){false}}
    queue.add(Node(y, x, 0))
    visited[y][x] = true
    var max = 0
    while(queue.isNotEmpty()){
        val (dy, dx, dd) = queue.removeFirst()
        if(dd > max) max = dd
        for(i in 0 until 4){
            val ny = dy + dir[i].first
            val nx = dx + dir[i].second
            if(ny < 0 || ny >= map.size || nx < 0 || nx >= map[0].size || visited[ny][nx] || !map[ny][nx]) continue
            visited[ny][nx] = true
            queue.add(Node(ny, nx, dd+1))
        }
    }
    return max
}

private data class Node(val y: Int, val x: Int, val dist: Int)