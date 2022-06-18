package boj.structure

import java.util.StringTokenizer

//[Cities and States] https://www.acmicpc.net/problem/14171
fun main() {
    val n = readLine()!!.toInt()
    val cities = HashMap<Pair<String, String>, Int>()
    var answer = 0
    repeat(n) {
        val (city, code) = readLine()!!.split(" ")
        val head = city.substring(0, 2)
        if(code == head) return@repeat
        cities[Pair(head, code)] = cities.getOrDefault(Pair(head,code), 0) + 1
        answer += cities.getOrDefault(Pair(code, head), 0)
    }
    println(answer)
}