package boj.tree

import java.util.*

//[트리와 쿼리] https://www.acmicpc.net/problem/15681
private val subtrees = mutableListOf<Tree>()

fun main() {
    val (n, r, q) = readLine()!!.split(' ').map { it.toInt() }
    val links = mutableMapOf<Int, MutableSet<Int>>()
    for (i in 0..n) subtrees.add(Tree(i))
    repeat(n - 1) {
        val (u, v) = readLine()!!.split(' ').map { it.toInt() }
        val uLink = links.getOrDefault(u, mutableSetOf())
        val vLink = links.getOrDefault(v, mutableSetOf())
        uLink.add(v)
        vLink.add(u)
        links[u] = uLink
        links[v] = vLink
    }
    val queue = LinkedList<Int>()
    queue.add(r)
    while (queue.isNotEmpty()) {
        val cur = queue.poll()
        links[cur]?.forEach {
            subtrees[cur].children.add(it)
            queue.add(it)
            links[it]?.remove(cur)
        }
    }
    subtrees[r].getSubTreeSize()

    repeat(q) {
        val s = readLine()!!.toInt()
        println(subtrees[s].size)
    }
}

private class Tree(val value: Int) {
    val children = mutableListOf<Int>()

    var size = 0

    fun getSubTreeSize(): Int {
        if (size != 0) return size
        var size = 1
        children.forEach {
            size += subtrees[it].getSubTreeSize()
        }
        this.size = size
        return size
    }
}