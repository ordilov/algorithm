import java.util.*

// [후위표기식] https://www.acmicpc.net/problem/1918
fun main() {
    val signs = readLine()!!.toCharArray()
    val op = Stack<Char>()
    val sb = StringBuilder()
    val map = mapOf('(' to 0, ')' to 0, '+' to 1, '-' to 1, '*' to 2, '/' to 2)

    for (sign in signs) {
        when (sign) {
            '(' -> op.push(sign)
            ')' -> {
                while (op.isNotEmpty() && op.peek() != '(') {
                    sb.append(op.pop())
                }
                op.pop()
            }
            '+', '-', '*', '/' -> {
                while(op.isNotEmpty() && map[op.peek()]!! >= map[sign]!!) {
                    sb.append(op.pop())
                }
                op.push(sign)
            }
            in 'A'..'Z' -> sb.append(sign)
        }
    }
    while(op.isNotEmpty()){
        sb.append(op.pop())
    }
    println(sb)
}