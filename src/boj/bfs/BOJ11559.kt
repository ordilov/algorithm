package boj.bfs

import java.util.*

//[Puyo Puyp] https://www.acmicpc.net/problem/11559
private lateinit var board: Array<CharArray>
private var dx = intArrayOf(0, 1, 0, -1)
private var dy = intArrayOf(1, 0, -1, 0)
private lateinit var visited: Array<BooleanArray>
private var list = mutableListOf<Node11559>()
private var n = 12
private var m = 6
fun main() {
    board = Array(n) { CharArray(m) }
    for (i in 0 until n) {
        board[i] = readLine()!!.toCharArray()
    }
    var count = 0
    var remains = true
    while (remains) {
        remains = false
        visited = Array(n) { BooleanArray(m) }
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (board[i][j] != '.') {
                    list = ArrayList()
                    bfs(board[i][j], i, j)
                    if (list.size >= 4) {
                        remains = true
                        for (k in list.indices) {
                            board[list[k].x][list[k].y] = '.'
                        }
                    }
                }
            }
        }
        if (!remains) break
        fallPuyos()
        count++
    }
    println(count)
}

private fun fallPuyos() {
    for (i in 0 until m) {
        for (j in n - 1 downTo 1) {
            if (board[j][i] != '.') continue
            for (k in j - 1 downTo 0) {
                if (board[k][i] == '.') continue
                board[j][i] = board[k][i]
                board[k][i] = '.'
                break
            }
        }
    }
}

private fun bfs(c: Char, x: Int, y: Int) {
    val q: Queue<Node11559> = LinkedList()
    list.add(Node11559(x, y))
    q.offer(Node11559(x, y))
    visited[x][y] = true
    while (!q.isEmpty()) {
        val current = q.poll()
        for (i in 0..3) {
            val nx = current.x + dx[i]
            val ny = current.y + dy[i]
            if (nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny] || board[nx][ny] != c) continue
            visited[nx][ny] = true
            list.add(Node11559(nx, ny))
            q.offer(Node11559(nx, ny))
        }
    }
}

private class Node11559(var x: Int, var y: Int)