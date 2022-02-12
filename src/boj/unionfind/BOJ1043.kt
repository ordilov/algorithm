package boj.unionfind

// [거짓말] https://www.acmicpc.net/problem/1043
var parent: Array<Int> = emptyArray()

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val parties = Array<MutableSet<Int>>(n + 1) { mutableSetOf() }
    parent = Array(n + 1) { it }

    val tPeople = readLine()!!.split(" ").map { it.toInt() }
    val t = tPeople[0]
    if (t == 0) {
        println(m)
        return
    }
    for (i in 1 until tPeople.size - 1) {
        union(tPeople[i], tPeople[i + 1])
    }

    repeat(m) {
        val pPeople = readLine()!!.split(" ").map { it.toInt() }
        for (i in 1 until pPeople.size - 1) {
            union(pPeople[i], pPeople[i + 1])
            parties[pPeople[i]].add(it)
        }
        parties[pPeople[pPeople.size - 1]].add(it)
    }

    val original = find(tPeople[1])
    val answer = Array(m) { false }
    for (i in 1..n) {
        val now = find(i)
        if (now != original) continue
        parties[i].forEach {
            answer[it] = true
        }
    }

    println(answer.count { !it })
}

private fun union(a: Int, b: Int) {
    val rootA = find(a)
    val rootB = find(b)
    if (rootA == rootB) return
    parent[rootB] = rootA
}

private fun find(x: Int): Int {
    if (parent[x] == x) return x
    parent[x] = find(parent[x])
    return parent[x]
}