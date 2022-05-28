package boj.string

//[부분 문자열] https://www.acmicpc.net/problem/16916
fun main() {
    val s = readLine()!!
    val p = readLine()!!
    println(kmp(s, p))
}

private fun makeTable(pattern: String): IntArray {
    val n = pattern.length
    val table = IntArray(n)

    var idx = 0
    for (i in 1 until n) {
        while (idx > 0 && pattern[i] != pattern[idx]) {
            idx = table[idx - 1]
        }
        if (pattern[i] != pattern[idx]) continue
        table[i] = ++idx
    }

    return table
}

private fun kmp(search: String, pattern: String): Int {
    val table = makeTable(pattern)
    var k = 0
    for (i in search.indices) {
        while (k > 0 && search[i] != pattern[k]) {
            k = table[k - 1]
        }
        if (search[i] != pattern[k]) continue
        if (k == pattern.length - 1) {
            return 1
        }
        k++
    }
    return 0
}