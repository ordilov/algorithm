package boj.string

// [IOIOI] https://www.acmicpc.net/problem/5525
fun main() {
    val n = readLine()!!.toInt()
    val m = readLine()!!.toInt()
    val s = readLine()!!

    val p = StringBuilder()
    for (i in 0..2 * n) {
        p.append(if (i % 2 == 0) "I" else "O")
    }

    val kmp = KMP(p.toString(), s)
    println(kmp.size)
}

private fun getTable(pattern: String): Array<Int> {
    val l = pattern.length
    val table = Array(l) { 0 }
    var j = 0

    for (i in 1 until l) {
        while (j > 0 && pattern[j] != pattern[i]) {
            j = table[j - 1]
        }
        if (pattern[j] == pattern[i]) {
            table[i] = ++j
        }
    }
    return table
}

private fun KMP(pattern: String, str: String): ArrayList<Int> {
    val list = ArrayList<Int>()
    val table = getTable(pattern)
    var j = 0

    for (i in str.indices) {
        while (j > 0 && pattern[j] != str[i]) {
            j = table[j - 1]
        }
        if (pattern[j] != str[i]) continue
        if (++j == pattern.length) {
            list.add(i - j + 1)
            j = table[j - 1]
        }
    }

    return list
}