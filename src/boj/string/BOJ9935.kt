package boj.string

// [문자열 폭발] https://www.acmicpc.net/problem/9935
fun main() {
    val str = readLine()!!
    val explode = readLine()!!
    val stack = mutableListOf<Char>()

    for (i in str.indices) {
        stack.add(str[i])

        if (stack.size < explode.length)
            continue

        var flag = true
        for (j in explode.indices) {
            if (stack[stack.size - explode.length + j] != explode[j]) {
                flag = false
                break
            }
        }
        if (!flag) continue
        for (j in explode.indices)
            stack.removeLast()
    }


    val sb = StringBuilder()
    for (i in stack) {
        sb.append(i)
    }
    if (sb.isBlank()) println("FRULA")
    else println(sb)
}