package boj.bfs

// [특정 거리의 도시 찾기] https://www.acmicpc.net/problem/18352
private lateinit var map: Array<MutableList<Int>>
private lateinit var distances: IntArray
private var n = 0;
private var m = 0;
private var k = 0;
private var x = 0;
fun main() {
    readLine()!!.split(" ").map { it.toInt() }.let {
        n = it[0]
        m = it[1]
        k = it[2]
        x = it[3]
    }
    map = Array(n) { mutableListOf() }
    distances = IntArray(n) { Int.MAX_VALUE }
    distances[x - 1] = 0
    for (i in 0 until m) {
        readLine()!!.split(" ").map { it.toInt() }.let {
            map[it[0] - 1].add(it[1] - 1)
        }
    }
    val queue = ArrayDeque<Node18352>()
    queue.add(Node18352(0, x - 1))
    while (queue.isNotEmpty()) {
        val (cd, cx) = queue.removeFirst()
        for (i in map[cx]) {
            if (i == 0 || cd + 1 >= distances[i]) continue
            distances[i] = cd + 1
            queue.add(Node18352(cd + 1, i))
        }
    }
    val list = mutableListOf<Int>()
    for (i in 0 until n) {
        if (distances[i] == k) list.add(i + 1)
    }
    if (list.isEmpty()) println(-1)
    else println(list.joinToString("\n"))
}

private data class Node18352(val distance: Int, val city: Int)