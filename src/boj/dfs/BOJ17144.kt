package boj.dfs

// [미세먼지 안녕!] https://www.acmicpc.net/problem/17144
private lateinit var map: Array<IntArray>
private lateinit var diffusion: Array<IntArray>
private var upAir = 0
private var downAir = 0
private var r = 0
private var c = 0
private var t = 0
private val dy = arrayOf(0, 0, 1, -1)
private val dx = arrayOf(1, -1, 0, 0)
fun main() {
    readLine()!!.split(" ").map { it.toInt() }.let {
        r = it[0]
        c = it[1]
        t = it[2]
    }
    map = Array(r) { IntArray(c) }
    diffusion = Array(r) { IntArray(c) }
    var air = false
    repeat(r) {
        val line = readLine()!!.split(" ").map { it.toInt() }
        for (i in 0 until c) {
            map[it][i] = line[i]
            if (air) continue
            if (line[i] == -1) {
                upAir = it
                downAir = it + 1
                air = true
            }
        }
    }

    repeat(t) {
        diffuse()
        cleanUp()
        cleanDown()
    }

    var count = 0
    for (i in 0 until r) {
        for (j in 0 until c) {
            if (map[i][j] == -1) continue
            count += map[i][j]
        }
    }
    println(count)
}

private fun diffuse() {
    diffusion.forEach { it.fill(0) }
    for (i in 0 until r) {
        for (j in 0 until c) {
            if (map[i][j] == -1 || map[i][j] == 0) continue
            val divide = map[i][j] / 5
            var count = 0
            for (k in 0 until 4) {
                val nx = j + dx[k]
                val ny = i + dy[k]
                if (nx < 0 || nx >= c || ny < 0 || ny >= r) continue
                if (map[ny][nx] == -1) continue
                diffusion[ny][nx] += divide
                count++
            }
            diffusion[i][j] -= divide * count
        }
    }

    for (i in 0 until r) {
        for (j in 0 until c) {
            map[i][j] += diffusion[i][j]
        }
    }
}

private fun cleanUp() {
    for (i in upAir - 1 downTo 1) {
        map[i][0] = map[i - 1][0]
    }
    for (i in 0 until c - 1) {
        map[0][i] = map[0][i + 1]
    }
    for (i in 1 until upAir + 1) {
        map[i - 1][c - 1] = map[i][c - 1]
    }
    for (i in c - 1 downTo 2) {
        map[upAir][i] = map[upAir][i - 1]
    }
    map[upAir][1] = 0
}

private fun cleanDown() {
    for (i in downAir + 1 until r - 1) {
        map[i][0] = map[i + 1][0]
    }
    for (i in 0 until c - 1) {
        map[r - 1][i] = map[r - 1][i + 1]
    }
    for (i in r - 1 downTo downAir + 1) {
        map[i][c - 1] = map[i - 1][c - 1]
    }
    for (i in c - 1 downTo 2) {
        map[downAir][i] = map[downAir][i - 1]
    }
    map[downAir][1] = 0
}
