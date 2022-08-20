package programmers.kakaointern19

class PRO64062 {
    fun solution(stones: IntArray, k: Int): Int {
        var lo = stones.minOf{it}
        var hi = stones.maxOf{it}
        var mid: Int
        var answer = 0

        while (lo <= hi) {
            mid = (lo + hi) / 2
            if (canMove(mid, stones, k )) {
                lo = mid + 1
                answer = Math.max(answer, mid)
            } else {
                hi = mid - 1
            }
        }
        return answer
    }

    private fun canMove(depth: Int, stones: IntArray, k: Int): Boolean {
        var skip = 0
        for (stone in stones) {
            if (stone - depth < 0)
                skip++
            else
                skip = 0
            if (skip == k)
                return false
        }
        return true
    }
}

fun main() {
    print(PRO64062().solution(intArrayOf(2, 4, 5, 3, 2, 1, 4, 2, 5, 1), 3))
}