import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val dx = arrayOf(-1, 1, 0, 0)
    val dy = arrayOf(0, 0, -1, 1)

    repeat(br.readLine()!!.toInt()) {
        val (col, row) = br.readLine()!!.split(" ").map { it.toInt() }
        val map = Array(row) { br.readLine()!!.toCharArray() }
        val save = Array(row) { IntArray(col) }
        var res = 0

        data class Pair(val x: Int, val y: Int) {
            fun isAvail(): Boolean {
                return (this.x in 0 until row) && (this.y in 0 until col)
            }
        }

        val queue = LinkedList<Pair>()

        for (i in 0 until row) {
            for (j in 0 until col) {
                if (map[i][j] != '@') continue
                queue.add(Pair(i, j))
                break
            }
        }
        for (i in 0 until row) {
            for (j in 0 until col) {
                if (map[i][j] != '*') continue
                queue.add(Pair(i, j))
            }
        }
        while (!queue.isEmpty()) {
            val (x, y) = queue.poll()
            if (map[x][y] == '@' && (x == 0 || y == 0 || x == row - 1 || y == col - 1)) {
                res = save[x][y] + 1
                break
            }
            for (i in 0 until 4) {
                val bx = x + dx[i]
                val by = y + dy[i]
                if (!Pair(bx, by).isAvail() || map[bx][by] == '#') continue
                if (map[bx][by] != '*' && map[x][y] == '*') {
                    map[bx][by] = '*'
                    queue.add(Pair(bx, by))
                    save[bx][by] = save[x][y] + 1
                } else if (map[x][y] == '@' && map[bx][by] == '.') {
                    map[bx][by] = '@'
                    queue.add(Pair(bx, by))
                    save[bx][by] = save[x][y] + 1
                }
            }
        }
        println(if (res == 0) "IMPOSSIBLE" else res)
    }
}