package boj.dp

import kotlin.math.abs

// [치킨 배달] https://www.acmicpc.net/problem/15686
private lateinit var map: Array<Array<Int>>
private lateinit var open: BooleanArray
private var houses = mutableListOf<Pair<Int, Int>>()
private var chickens = mutableListOf<Pair<Int, Int>>()
private var answer = Int.MAX_VALUE
private var m = 0
private var n = 0

fun main() {
    readLine()!!.split(" ").map { it.toInt() }.let {
        n = it[0]
        m = it[1]
    }
    map = Array(n) { Array(n) { 0 } }
    repeat(n) {
        map[it] = readLine()!!.split(" ").map { it.toInt() }.toTypedArray()
        for (i in 0 until n) {
            if (map[it][i] == 0) continue
            if (map[it][i] == 1) {
                houses.add(Pair(it, i))
            } else if (map[it][i] == 2) {
                chickens.add(Pair(it, i))
            }
        }
    }
    open = BooleanArray(chickens.size)
    dfs(0, 0)
    println(answer)
}

private fun dfs(start: Int, count: Int) {
    if (count == m) {
        var res = 0
        for (house in houses) {
            var temp = Int.MAX_VALUE
                for (j in chickens.indices) {
                if (!open[j]) continue
                val (y, x) = chickens[j]
                val distance =
                    abs(house.first - y) + abs(house.second - x)
                temp = minOf(temp, distance)
            }
            res += temp
        }
        answer = minOf(answer, res)
        return
    }

    for (i in start until chickens.size) {
        open[i] = true
        dfs(i + 1, count + 1)
        open[i] = false
    }
}
