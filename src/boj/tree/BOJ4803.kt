package boj.tree

// [트리] https://www.acmicpc.net/problem/4803
fun main() {
    var index = 1
    while (true) {
        val (n, m) = readLine()!!.split(" ").map { it.toInt() }
        if (n == 0 && m == 0) break

        val parent = Array(n + 1) { it }
        val cycles = Array(n + 1) { false }

        repeat(m) {
            val (a, b) = readLine()!!.split(" ").map { it.toInt() }
            val parentA = find(parent, a)
            val parentB = find(parent, b)
            if(cycles[parentA] || cycles[parentB] || parentA == parentB) {
                cycles[parentA] = true
                cycles[parentB] = true
            }
            parent[parentA] = parentB
        }
        repeat(n + 1) {
            val p = find(parent, it)
            if (cycles[p]) parent[it] = 0
        }
        val size = parent.distinct().size - 1
        print("Case ${index++}: ")
        when (size) {
            0 -> println("No trees.")
            1 -> println("There is one tree.")
            else -> println("A forest of $size trees.")
        }
    }
}

private fun find(parent: Array<Int>, x: Int): Int {
    if (parent[x] == x) return x
    val findParent = find(parent, parent[x])
    parent[x] = findParent
    return findParent
}