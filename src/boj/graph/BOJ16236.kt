package boj.graph

import java.util.*

// [아기 상어] https://www.acmicpc.net/problem/16236

private var time = 0
private var shark: Shark = Shark(0, 0, 2, 0)
private val dy = arrayOf(-1, 0, 0, 1)
private val dx = arrayOf(0, -1, 1, 0)
private var map = arrayOf<IntArray>()
fun main() {
    val n = readLine()!!.toInt()
    map = Array(n + 1) { IntArray(n + 1) { 0 } }
    repeat(n) {
        val st = StringTokenizer(readLine()!!)
        for (i in 1..n) {
            val now = st.nextToken().toInt()
            if (now == 0) continue
            if (now == 9) {
                shark.move(it + 1, i)
                continue
            }
            map[it + 1][i] = now
        }
    }
    bfs(shark.y, shark.x)
    println(time)
}

private fun bfs(startY: Int, startX: Int) {
    val visited = Array(map.size) { BooleanArray(map[0].size) { false } }
    val queue = LinkedList<Pair<Int, Int>>()
    queue.add(Pair(startY, startX))
    var ey = Int.MAX_VALUE
    var ex = Int.MAX_VALUE
    var d = 0

    while (queue.isNotEmpty()) {
        d++
        for (k in 0 until queue.size) {
            val (y, x) = queue.poll()
            for (i in dy.indices) {
                val nextY = y + dy[i]
                val nextX = x + dx[i]
                if (nextY < 1 || nextY >= map.size || nextX < 1 || nextX >= map[0].size) continue
                if (visited[nextY][nextX]) continue
                if (map[nextY][nextX] > shark.size) continue
                if (map[nextY][nextX] < shark.size && map[nextY][nextX] != 0){
                    if (ey > nextY){
                        ey = nextY
                        ex = nextX
                    } else if (ey == nextY && ex > nextX){
                        ey = nextY
                        ex = nextX
                    }
                }
                visited[nextY][nextX] = true
                queue.add(Pair(nextY, nextX))
            }
        }
        if(ey != Int.MAX_VALUE && ex != Int.MAX_VALUE)  break
    }
    if(ey == Int.MAX_VALUE && ex == Int.MAX_VALUE)  return
    shark.eat(ey, ex)
    time += d
    bfs(ey, ex)
}


private class Shark(var y: Int, var x: Int, var size: Int, var eat: Int) {
    fun move(y: Int, x: Int) {
        this.y = y
        this.x = x
        map[y][x] = 0
    }

    fun eat(y: Int, x: Int) {
        move(y, x)
        eat++
        if (eat != size) return
        size++
        eat = 0
    }
}