package boj.segmenttree

// [구간 합 구하기 4] https://www.acmicpc.net/problem/11659

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val arr = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    val tree = IntArray(4 * n)
    init(0, n - 1, tree, arr, 1)

    val sb = StringBuilder()
    repeat(m) {
        val (l, r) = readLine()!!.split(" ").map { it.toInt() }
        sb.append(sum(0, n-1, tree, 1, l-1, r-1)).append("\n")
    }
    println(sb)
}

private fun init(start: Int, end: Int, tree: IntArray, arr: IntArray, index: Int): Int {
    if (start == end) {
        tree[index] = arr[start]
        return arr[start]
    }
    val mid = (start + end) / 2
    val left = init(start, mid, tree, arr, index * 2)
    val right = init(mid + 1, end, tree, arr, index * 2 + 1)
    tree[index] = left + right
    return tree[index]
}

private fun sum(start: Int, end: Int, tree: IntArray, index: Int, l: Int, r: Int): Int {
    if (l > end || r < start) return 0
    if (l <= start && end <= r) return tree[index]
    val mid = (start + end) / 2
    return sum(start, mid, tree, index * 2, l, r) + sum(mid + 1, end, tree, index * 2 + 1, l, r)
}