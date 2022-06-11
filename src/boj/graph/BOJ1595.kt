package boj.graph

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

//[북쪽나라의 도로] https://www.acmicpc.net/problem/1595
private val list = Array<MutableList<Node1595>>(10001) { mutableListOf() }
private lateinit var check: BooleanArray
private var idx = 0
private var max: Long = -1

fun main() {
    var none = true
    val br = BufferedReader(InputStreamReader(System.`in`))
    while (true) {
        try {
            val (a, b, v) = br.readLine()!!.split(" ").map { it.toInt() }
            none = false
            list[a].add(Node1595(b, v))
            list[b].add(Node1595(a, v))
        } catch (e: java.lang.Exception) {
            break
        }
    }

    if (none) {
        println(0)
        return
    }
    check = BooleanArray(10001)
    check[1] = true
    dfs(1, 0)
    Arrays.fill(check, false)
    check[idx] = true
    max = -1
    dfs(idx, 0)
    println(max)
}

fun dfs(here: Int, cost: Long) {
    for (next in list[here]) {
        if (check[next.to]) continue
        val nextValue = cost + next.value
        check[next.to] = true
        if (max < nextValue) {
            idx = next.to
            max = nextValue
        }
        dfs(next.to, nextValue)
    }
}

private data class Node1595(var to: Int, var value: Int)