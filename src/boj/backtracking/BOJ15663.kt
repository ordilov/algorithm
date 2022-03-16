package boj.backtracking

//[N과 M(9)] https://www.acmicpc.net/problem/15663
private val sb = StringBuilder()
private lateinit var list: List<Int>
private val total = mutableSetOf<String>()
fun main(){
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    list = readLine()!!.split(" ").map { it.toInt() }
    list = list.sorted()
    track(m, BooleanArray(n), mutableListOf())
    println(total.joinToString("\n"))
}

private fun track(size: Int, visited: BooleanArray, arr: MutableList<Int>) {
    if (arr.size === size) {
        total.add(arr.joinToString(" "))
        return
    }

    for (i in list.indices) {
        if(visited[i]) continue
        visited[i] = true
        arr.add(list[i])
        track(size, visited, arr)
        visited[i] = false
        arr.removeAt(arr.size - 1)
    }
}