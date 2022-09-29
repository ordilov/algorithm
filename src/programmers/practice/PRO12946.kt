package programmers.practice


fun main() {
    val solution = PRO12946()
    println(solution.solution(2))
}

class PRO12946 {
    private val list = ArrayList<IntArray>()
    fun solution(n: Int): Array<IntArray> {
        recursive(n, 1, 3, 2)
        return list.toTypedArray()
    }

    private fun recursive(n: Int, a: Int, b: Int, c: Int) {
        if (n == 1) {
            list.add(intArrayOf(a, b))
            return
        }
        recursive(n - 1, a, c, b)
        list.add(intArrayOf(a, b))
        recursive(n - 1, c, b, a)
    }
}