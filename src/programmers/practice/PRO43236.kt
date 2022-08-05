package programmers.practice

class PRO43236 {
    fun solution(distance: Int, rocks: IntArray, n: Int): Int {
        rocks.sort()
        var answer = 0
        var left = 1
        var right = distance
        var mid: Int

        while (left <= right) {
            var cnt = 0
            var prev = 0
            mid = (left + right) / 2

            for (i in rocks.indices) {
                if (rocks[i] - prev < mid) {
                    cnt++
                } else {
                    prev = rocks[i]
                }
            }

            if (distance - prev < mid) cnt++

            if (cnt <= n) {
                answer = answer.coerceAtLeast(mid)
                left = mid + 1
            } else {
                right = mid - 1
            }
        }

        return answer
    }
}