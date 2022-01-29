package boj.implementation

//[하얀 칸] https://www.acmicpc.net/problem/1100
fun main() {
    var answer = 0
    for (i in 0 until 8) {
        val line = readLine()
        if (line != null) {
            for (j in line.indices) {
                if ((i % 2 == j % 2) && line[j] == 'F')
                    answer++

            }
        }
    }
    println(answer)
}