// [N과 M(5)] https://www.acmicpc.net/problem/15654
var arr: List<Int> = listOf()
var n: Int = 0
var m: Int = 0
fun main() {
    val t = readLine()!!.split(" ").map { it.toInt() }
    n = t[0]
    m = t[1]
    arr = readLine()!!.split(" ").map { it.toInt() }
    arr = arr.sorted()
    dfs(BooleanArray(n), mutableListOf<Int>())
}

private fun dfs(visited: BooleanArray, picked: MutableList<Int>) {
    if (picked.size == m) {
        println(picked.joinTo(StringBuffer(), " "))
        return
    }
    for (i in 0 until n) {
        if (visited[i]) continue
        visited[i] = true
        picked.add(arr[i])
        dfs(visited, picked)
        visited[i] = false
        picked.removeAt(picked.size - 1)
    }

}
