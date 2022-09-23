package structure

class SegmentTree(val a: IntArray) {
    private val number = a.size
    val tree = IntArray(number * 4)

    fun init(start: Int, end: Int, node: Int): Int {
        if (start == end) {
            tree[node] = a[start]
            return tree[node]
        }
        val mid = (start + end) / 2
        tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1)
        return tree[node]
    }

    fun sum(start: Int, end: Int, node: Int, left: Int, right: Int): Int {
        if (left > end || right < start) return 0
        if (left <= start && end <= right) return tree[node]
        val mid = (start + end) / 2
        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right)
    }

    fun update(start: Int, end: Int, node: Int, index: Int, diff: Int) {
        if (index < start || index > end) return
        tree[node] += diff
        if (start == end) return
        val mid = (start + end) / 2

        update(start, mid, node * 2, index, diff)
        update(mid + 1, end, node * 2 + 1, index, diff)
    }


}

fun main() {
    val a = intArrayOf(1, 9, 3, 8, 4, 5, 5, 9, 10, 3, 4, 5)
    val tree = SegmentTree(a)
    val end = a.size - 1
    tree.init(0, end, 1)
    println(tree.sum(0, end, 1, 0, 12))
    println(tree.sum(0, end, 1, 0, 2))
}