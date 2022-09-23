package boj.segmenttree

private lateinit var a: IntArray
private lateinit var tmp: IntArray
private var answer = 0L

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    a = readLine().split(" ").map { it.toInt() }.toIntArray()
    tmp = IntArray(n)
    mergeSort(0, n - 1)
    println(answer)
}

fun mergeSort(left: Int, right: Int) {
    if (left >= right) return
    val mid = (left + right) / 2
    mergeSort(left, mid)
    mergeSort(mid + 1, right)
    merge(left, mid, right)
}

fun merge(left: Int, mid: Int, right: Int) {
    var idx = left
    var leftIdx = left
    var rightIdx = mid + 1
    var count = 0
    while (leftIdx <= mid || rightIdx <= right) {
        if (leftIdx > mid) {
            tmp[idx++] = a[rightIdx++]
            count++
        } else if (rightIdx > right) {
            tmp[idx++] = a[leftIdx++]
            answer += count
        } else if (a[leftIdx] <= a[rightIdx]) {
            tmp[idx++] = a[leftIdx++]
            answer += count
        } else {
            tmp[idx++] = a[rightIdx++]
            count++
        }
    }
    for (i in left..right) {
        a[i] = tmp[i]
    }
}