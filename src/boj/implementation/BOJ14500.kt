package boj.implementation

// [테트로미노] https://www.acmicpc.net/problem/14500

var map = arrayOf(intArrayOf())
fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    map = Array(n) { IntArray(m) { 0 } }
    repeat(n) {
        map[it] = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    }

    var max = 0
    for (i in 0 until n) {
        for (j in 0 until m) {
            max = maxOf(max, sum(i, j))
        }
    }

    println(max)

}

private fun sum(y: Int, x: Int): Int {
    var sum = 0
    val ySize = map.size
    val xSize = map[0].size

    if (y + 3 < ySize) {
        sum = sum.coerceAtLeast(map[y][x] + map[y + 1][x] + map[y + 2][x] + map[y + 3][x])
    }

    if (x + 3 < xSize) {
        sum = sum.coerceAtLeast(map[y][x] + map[y][x + 1] + map[y][x + 2] + map[y][x + 3])
    }

    if (y + 1 < ySize && x + 1 < xSize) {
        sum = sum.coerceAtLeast(map[y][x] + map[y + 1][x + 1] + map[y + 1][x] + map[y][x + 1])
    }

    if (y + 2 < ySize && x + 1 < xSize) {
        sum = sum.coerceAtLeast(map[y][x] + map[y + 1][x] + map[y + 2][x] + map[y + 2][x + 1])
        sum = sum.coerceAtLeast(map[y][x] + map[y + 1][x] + map[y + 2][x] + map[y + 1][x + 1])
        sum = sum.coerceAtLeast(map[y][x] + map[y + 1][x] + map[y + 2][x] + map[y][x + 1])

        sum = sum.coerceAtLeast(map[y][x] + map[y][x + 1] + map[y + 1][x + 1] + map[y + 2][x + 1])
        sum = sum.coerceAtLeast(map[y + 1][x] + map[y][x + 1] + map[y + 1][x + 1] + map[y + 2][x + 1])
        sum = sum.coerceAtLeast(map[y + 2][x] + map[y][x + 1] + map[y + 1][x + 1] + map[y + 2][x + 1])

        sum = sum.coerceAtLeast(map[y][x] + map[y + 1][x] + map[y + 1][x + 1] + map[y + 2][x + 1])
        sum = sum.coerceAtLeast(map[y][x + 1] + map[y + 1][x] + map[y + 1][x + 1] + map[y + 2][x])
    }

    if (y + 1 < ySize && x + 2 < xSize) {
        sum = sum.coerceAtLeast(map[y][x] + map[y][x + 1] + map[y][x + 2] + map[y + 1][x])
        sum = sum.coerceAtLeast(map[y][x] + map[y][x + 1] + map[y][x + 2] + map[y + 1][x + 1])
        sum = sum.coerceAtLeast(map[y][x] + map[y][x + 1] + map[y][x + 2] + map[y + 1][x + 2])

        sum = sum.coerceAtLeast(map[y][x] + map[y + 1][x + 1] + map[y + 1][x + 2] + map[y + 1][x])
        sum = sum.coerceAtLeast(map[y][x + 1] + map[y + 1][x + 1] + map[y + 1][x + 2] + map[y + 1][x])
        sum = sum.coerceAtLeast(map[y][x + 2] + map[y + 1][x + 1] + map[y + 1][x + 2] + map[y + 1][x])

        sum = sum.coerceAtLeast(map[y][x] + map[y][x + 1] + map[y + 1][x + 1] + map[y + 1][x + 2])
        sum = sum.coerceAtLeast(map[y+1][x] + map[y][x + 1] + map[y + 1][x + 1] + map[y][x + 2])
    }

    return sum
}