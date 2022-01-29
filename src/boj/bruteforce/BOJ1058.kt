package boj.bruteforce

//[친구] https://www.acmicpc.net/problem/1058
fun main() {
    val n = readLine()!!.toInt()
    val twoFriends = Array(n) { setOf<Int>() }

    repeat(n) { i ->
        twoFriends[i] = readLine()!!.mapIndexedNotNull { index, c -> if (c == 'Y') index else null }.toSet()
    }
    var max = 0
    repeat(n) { i ->
        val now = twoFriends[i].toMutableSet()
        twoFriends[i].forEach { now.addAll(twoFriends[it]) }
        now.remove(i)
        max = maxOf(max, now.size)
    }
    println(max)
}