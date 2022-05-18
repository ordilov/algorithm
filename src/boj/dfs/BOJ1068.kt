package boj.dfs

import java.util.*

//[트리] https://www.acmicpc.net/problem/1068
fun main() {
    val n = readLine()!!.toInt()
    val tree = MutableList<MutableList<Int>>(n) { mutableListOf() }
    val parents = IntArray(n)
    val sizes = IntArray(n)
    val st = StringTokenizer(readLine())
    for (i in 0 until n) {
        val parent = st.nextToken().toInt()
        if (parent == -1) {
            continue
        }
        parents[i] = parent
        tree[parent].add(i)
        sizes[parent]++
    }
    val remove = readLine()!!.toInt()
    removeNode(remove, tree, parents, sizes)
    var count = 0
    for (i in 0 until tree.size) {
        if (parents[i] == -2 || sizes[i] != 0) continue
        count++
    }
    println(count)
}

private fun removeNode(
    now: Int,
    tree: MutableList<MutableList<Int>>,
    parents: IntArray,
    sizes: IntArray
) {
    val nodes = tree.get(now)
    nodes.forEach { removeNode(it, tree, parents, sizes) }
    sizes[parents[now]]--
    parents[now] = -2
}