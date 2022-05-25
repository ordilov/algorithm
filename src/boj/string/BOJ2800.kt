package boj.string

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

//[괄호 제거] https://www.acmicpc.net/problem/2800

private lateinit var str: String
private lateinit var set: MutableSet<String>
private val stack = Stack<Int>()
private lateinit var arr: IntArray

fun main() {
    val `in` = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(`in`.readLine())
    val list = mutableListOf<String>()
    set = mutableSetOf()
    str = st.nextToken()
    arr = IntArray(str.length)
    var index = 1
    for (i in str.indices) {
        if (str[i] == '(') arr[i] = index++ else if (str[i] == ')') arr[i] = --index
    }
    dfs(0, String())
    list.addAll(set)
    list.sort()
    for (i in 1 until list.size) println(list[i])
}

fun dfs(dep: Int, s: String) {
    if (dep == str.length) {
        set.add(s)
        return
    }

    val now = str[dep]
    if (now != '(' && now != ')') {
        dfs(dep + 1, s + now)
        return
    }

    if (now == '(') {
        stack.add(arr[dep])
        dfs(dep + 1, "$s(")
        stack.pop()
        dfs(dep + 1, s)
    }
    if (now == ')') {
        if (stack.isEmpty() || stack.peek() != arr[dep]) {
            dfs(dep + 1, s)
            return
        }
        stack.pop()
        dfs(dep + 1, "$s)")
        stack.add(arr[dep])
    }
}