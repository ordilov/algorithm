package boj.bruteforce

// [N과 M (12)] https://www.acmicpc.net/problem/15666
val set = mutableSetOf<String>()
private lateinit var arr: List<Int>

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    arr = readLine()!!.split(" ").map { it.toInt() }.sorted()
    track(mutableListOf(), m)
}

private fun track(acc: MutableList<Int>, m: Int, start: Int = 0) {
    if (acc.size == m) {
        val message = acc.joinToString(" ")
        if (set.contains(message)) return
        set.add(message)
        println(message)
        return
    }

    for (i in start until arr.size) {
        acc.add(arr[i])
        track(acc, m, i)
        acc.removeAt(acc.size - 1)
    }
}
