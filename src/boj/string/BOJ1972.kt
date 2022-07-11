package boj.string

// [놀라운 문자열] https://www.acmicpc.net/problem/1972
fun main() {
    val sb = StringBuilder()
    while (true) {
        val line = readLine()!!
        if (line == "*") {
            break
        }
        var d = true
        for (j in 1 until line.length) {
            val set = HashSet<String>()
            for (i in 0 until line.length - j) {
                val s = line.substring(i, i + 1) + line.substring(i + j, i + j  + 1)
                if (set.contains(s)) {
                    d = false
                    break
                }
                set.add(s)
            }
        }
        val n = if(d) "" else "NOT "
        sb.append("$line is ${n}surprising.\n")
    }
    println(sb)
}
