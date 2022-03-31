package boj.tree

// [LCA] https://www.acmicpc.net/problem/11437

private lateinit var graph: Array<ArrayList<Int>>
private lateinit var parent: Array<Int>
private lateinit var depth: Array<Int>
private lateinit var visited: BooleanArray
private val sb = StringBuilder()
fun main() {
    val n = readLine()!!.toInt()

    graph = Array(n + 1) { ArrayList() }
    parent = Array(n + 1) { it }
    depth = Array(n + 1) { 0 }
    visited = BooleanArray(n + 1) { false }
    repeat(n - 1) {
        val (a, b) = readLine()!!.split(" ").map { it.toInt() }
        graph[a].add(b)
        graph[b].add(a)
    }

    buildTree(1)

    val m = readLine()!!.toInt()
    repeat(m) {
        val (c, d) = readLine()!!.split(" ").map { it.toInt() }
        sb.append(lca(c, d)).append("\n")
    }
    println(sb)
}

private fun lca(a: Int, b: Int): Any {
    var ac = a
    var bc = b
    while (depth[ac] != depth[bc]) {
        if (depth[ac] > depth[bc])
            ac = parent[ac]
        else
            bc = parent[bc]
    }

    while (ac != bc) {
        ac = parent[ac]
        bc = parent[bc]
    }
    return ac
}

private fun buildTree(v: Int, d: Int = 0) {
    visited[v] = true
    depth[v] = d

    for (child in graph[v]) {
        if (visited[child]) continue
        parent[child] = v
        buildTree(child, d + 1)
    }
}
