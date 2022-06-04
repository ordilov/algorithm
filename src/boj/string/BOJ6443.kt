package boj.string

import java.util.*

//[애너그램] https://www.acmicpc.net/problem/6443
private val set = TreeSet<String>()
private lateinit var check: IntArray
private lateinit var s: Stack<Char>
fun main() {
    val sb = StringBuilder()
    val wordCount = readLine()!!.toInt()
    Array(wordCount) { readLine()!! }.forEach { str ->
        run {
            check = IntArray(26)
            for (c in str) {
                check[c - 'a']++
            }
            s = Stack()
            search(str.length)
            set.forEach { s -> sb.append("${s}\n") }
            set.clear()
        }
    }
    println(sb)
}

private fun search(r: Int) {
    if (r == s.size) {
        val sb = StringBuilder()
        for (c in s) {
            sb.append(c)
        }
        set.add(sb.toString())
    }

    for (i in 0 until 26) {
        if (check[i] <= 0) continue
        check[i]--
        s.push(Char(i + 'a'.code))
        search(r)
        s.pop()
        check[i]++
    }
}