package boj.dp

//[내려가기] https://www.acmicpc.net/problem/2096
fun main() {
    val n = readLine()!!.toInt()
    val min = IntArray(3)
    val max = IntArray(3)
    val minTemp = IntArray(3)
    val maxTemp = IntArray(3)

    for (i in 0 until n) {
        val line = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
        for (j in 0 until 3) {
            minTemp[j] = min[1]
            if (j != 0) minTemp[j] = minOf(minTemp[j], min[2])
            if (j != 2) minTemp[j] = minOf(minTemp[j], min[0])
        }
        for (j in 0 until 3) {
            min[j] = minTemp[j] + line[j]
            max[j] = maxTemp[j] + line[j]
        }
    }

    var maxR = 0
    var minR = Int.MAX_VALUE
    for (i in 0 until 3) {
        minR = minOf(min[i], minR)
        maxR = maxOf(max[i], maxR)
    }

    println("$maxR $minR")

}