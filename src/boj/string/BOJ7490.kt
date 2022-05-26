package boj.string

//[0 만들기] https://www.acmicpc.net/problem/7490
private val sb = StringBuilder()
private var n = 0
fun main() {
    val t = readLine()!!.toInt()
    repeat(t) {
        n = readLine()!!.toInt()
        dfs(1, 1, 1, 0, "1")
        sb.append("\n")
    }
    println(sb)
}

private fun dfs(now: Int, num: Int, sign: Int, sum: Int, str: String) {
    if (n == now) {
        if (sum + (num * sign) == 0) {
            sb.append(str + "\n")
        }
        return
    }

    dfs(now + 1, num * 10 + (now + 1), sign, sum, "$str ${now + 1}")
    dfs(now + 1, now + 1, 1, sum + (num * sign), "$str+${now + 1}")
    dfs(now + 1, now + 1, -1, sum + (num * sign), "$str-${now + 1}")
}