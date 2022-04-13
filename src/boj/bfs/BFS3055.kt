package boj.bfs

// [탈출] https://www.acmicpc.net/problem/3055
private var r = 0
private var c = 0
private lateinit var map: Array<CharArray>
private val dir = arrayOf(Pair(1, 0), Pair(0, 1), Pair(-1, 0), Pair(0, -1))
fun main() {
    readLine()!!.split(' ').let {
        r = it[0].toInt()
        c = it[1].toInt()
    }
    map = Array(r) { CharArray(c) }
    var sr = 0
    var sc = 0
    for (i in 0 until r) {
        map[i] = readLine()!!.toCharArray()
        for(j in 0 until c) {
            if(map[i][j] == 'S') {
                sr = i
                sc = j
            }
        }
    }
    val answer = bfs(sr, sc)
    if(answer == -1) println("KAKTUS")
    else println(answer)
}

private fun bfs(y: Int, x: Int): Int {
    var count = 1
    val queue = ArrayDeque<Pair<Int, Int>>()
    queue.add(Pair(y, x))

    while (true) {
        for (i in 0 until r) {
            for (j in 0 until c) {
                if (map[i][j] != '*') continue
                for (k in 0 until 4) {
                    val ny = i + dir[k].first
                    val nx = j + dir[k].second
                    if (ny < 0 || ny >= r || nx < 0 || nx >= c) continue
                    if (map[ny][nx] != '.' && map[ny][nx] != 'S') continue
                    map[ny][nx] = 'C'
                }
            }
        }

        for (i in 0 until r) {
            for (j in 0 until c) {
                if (map[i][j] != 'C') continue
                map[i][j] = '*'
            }
        }

        var noWay = true
        for(i in 0 until queue.size) {
            val (y, x) = queue.removeFirst()
            for (k in 0 until 4) {
                val ny = y + dir[k].first
                val nx = x + dir[k].second
                if (ny < 0 || ny >= r || nx < 0 || nx >= c) continue
                if(map[ny][nx] == 'D') {
                    return count
                }
                if (map[ny][nx] != '.') continue
                noWay = false
                map[ny][nx] = 'S'
                queue.add(Pair(ny, nx))
            }
        }

        if(noWay) return -1
        count++
    }
}