package boj.bfs

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

// [숨바꼭질 4] https://www.acmicpc.net/problem/13913

private val br = BufferedReader(InputStreamReader(System.`in`))
private var N = 0
private var K = 0
private val visited = IntArray(100001)
private val parent = IntArray(100001)

fun main(args: Array<String>) {
    val st = StringTokenizer(br.readLine())
    N = st.nextToken().toInt()
    K = st.nextToken().toInt()

    bfs(N, K)
    println(visited[K] - 1)
    val s = Stack<Int>()
    var idx = K
    while (idx != N) {
        s.push(idx)
        idx = parent[idx]
    }
    s.push(idx)
    while (!s.isEmpty()) {
        print(s.pop().toString() + " ")
    }
    br.close()
}

private fun bfs(start: Int, end: Int) {
    val q: Queue<Int> = LinkedList()
    q.add(start)
    visited[start] = 1
    while (!q.isEmpty()) {
        val now = q.poll()
        if (now + 1 <= 100000 && visited[now + 1] == 0) {
            visited[now + 1] = visited[now] + 1
            parent[now + 1] = now
            q.add(now + 1)
        }
        if (now - 1 >= 0 && visited[now - 1] == 0) {
            visited[now - 1] = visited[now] + 1
            parent[now - 1] = now
            q.add(now - 1)
        }
        if (now * 2 <= 100000 && visited[now * 2] == 0) {
            visited[now * 2] = visited[now] + 1
            parent[now * 2] = now
            q.add(now * 2)
        }
        if (visited[end] != 0) return
    }
}