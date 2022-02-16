package boj.graph

import java.util.*

//[ACM Craft] https://www.acmicpc.net/problem/1005
fun main() {
    val t = readLine()!!.toInt()
    repeat(t) {
        val (n, k) = readLine()!!.split(" ").map { it.toInt() }
        val times = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
        val routes = Array(n + 1) { mutableSetOf<Int>() }
        val connected = Array(n + 1) { 0 }
        repeat(k) {
            val (a, b) = readLine()!!.split(" ").map { it.toInt() }
            routes[a].add(b)
            connected[b]++
        }
        val w = readLine()!!.toInt()
        val q = LinkedList<Int>();
        val result = Array(n + 1) { 0 }
        for(i in 1 .. n){
            result[i] = times[i - 1]
            if(connected[i] == 0) q.add(i)
        }

        while(q.isNotEmpty()){
            val cur = q.poll()
            for(next in routes[cur]){
                result[next] = result[next].coerceAtLeast(result[cur] + times[next - 1])
                if(--connected[next] == 0) q.add(next)
            }
        }

        println(result[w]);
    }
}