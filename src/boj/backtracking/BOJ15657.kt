package boj.backtracking

import java.util.Collections.sort

//[N과 M (8)] https://www.acmicpc.net/problem/15657

private lateinit var arr: List<Int>
private val sb = StringBuilder()
fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    arr = readLine()!!.split(" ").map { it.toInt() }
    sort(arr)

    track(m, 0, mutableListOf())
    println(sb.toString())
}

private fun track(size: Int, start: Int, list: MutableList<Int>) {
    if (list.size == size) {
        sb.append(list.joinToString(" ")).append("\n")
        return
    }

    for (i in start until arr.size) {
        list.add(arr[i])
        track(size, i, list)
        list.removeAt(list.size - 1)
    }
}