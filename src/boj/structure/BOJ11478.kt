package boj.structure

//[서로 다른 부분 문자열의 개수] https://www.acmicpc.net/problem/11478
fun main() {
    val s = readLine()!!
    val set = HashSet<String>()

    for (i in s.indices) {
        for (j in i + 1..s.length) {
            set.add(s.substring(i, j))
        }
    }
    println(set.size)
}