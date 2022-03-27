package boj.dp

// [A → B] https://www.acmicpc.net/problem/16953
fun main() {
    var (a, b) = readLine()!!.split(" ").map { it.toInt() }
    var count = 1
    while (b != a) {
        if (b < a) {
            count = -1
            break
        }

        if (b % 10 != 1 && b % 2 != 0) {
            count = -1
            break
        }

        b /= if (b % 2 == 0) {
            2
        } else {
            10
        }
        count++
    }

    println(count)
}