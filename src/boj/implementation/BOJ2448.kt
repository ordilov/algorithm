package boj.implementation

// [별 찍기 - 11] https://www.acmicpc.net/problem/2448
fun main() {
    val n = readLine()!!.toInt()

    val map = Array(n) { CharArray(2 * n - 1) }
    map.forEach { it.fill(' ') }

    draw(0, n-1, n, map)
    map.forEach { println(it) }
//    map[0].forEach { print(it) }
}

private fun draw(x: Int, y: Int, n: Int, map: Array<CharArray>) {
    if (n == 3) {
        map[x][y] = '*'
        map[x+1][y-1] = '*'
        map[x+1][y+1] = '*'
        map[x+2][y-2] = '*'
        map[x+2][y-1] = '*'
        map[x+2][y] = '*'
        map[x+2][y+1] = '*'
        map[x+2][y+2] = '*'
        return
    }

    draw(x, y, n / 2, map)
    draw(x + n / 2, y - n / 2, n / 2, map)
    draw(x + n / 2, y + n / 2, n / 2, map)
}

