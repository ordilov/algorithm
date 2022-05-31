package boj.string

//[종점] https://www.acmicpc.net/problem/22867
fun main() {
    val n = readLine()!!.toInt()
    val time = mutableListOf<Pair<String, Int>>()
    repeat(n){
        readLine()!!.split(" ").let {
            time.add(Pair(it[0], 1))
            time.add(Pair(it[1], -1))
        }
    }
    time.sortBy { it.first }
    var answer = 0
    var result = 0
    for (i in 0 until n) {
        result += time[i].second
        answer = Math.max(result, answer)
    }
    println(answer)
}