package boj.dp


// [파이프 옮기기 1] https://www.acmicpc.net/problem/17070
private lateinit var map: Array<BooleanArray>
private var ans = 0
private var n = 0
fun main() {
    n = readLine()!!.toInt()
    map = Array(n + 1) { BooleanArray(n + 1) { false } }
    repeat(n) {
        val line =  readLine()!!.split(" ").map { it.toInt() == 0 }.toBooleanArray()
        for(i in 1 .. n){
            map[it + 1][i] = line[i - 1]
        }
    }
    dfs(1, 2, 0)
    println(ans)
}

private fun dfs(x: Int, y: Int, direction: Int) {
    if (x == n && y == n) {
        ans++
        return
    }
    when (direction) {
        0 -> if (y + 1 <= n && map[x][y + 1]) {
            dfs(x, y + 1, 0)
        }
        1 -> if (x + 1 <= n && map[x + 1][y]) {
            dfs(x + 1, y, 1)
        }
        2 -> {
            if (y + 1 <= n && map[x][y + 1]) {
                dfs(x, y + 1, 0)
            }
            if (x + 1 <= n && map[x + 1][y]) {
                dfs(x + 1, y, 1)
            }
        }
    }

    if (y + 1 <= n && x + 1 <= n && map[x][y + 1] && map[x + 1][y] && map[x + 1][y + 1]) {
        dfs(x + 1, y + 1, 2)
    }
}