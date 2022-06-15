import java.util.*

//[세 개의 소수 문제] https://www.acmicpc.net/problem/11502
internal object Main {
    var isPrime = BooleanArray(1001)
    var primeList = IntArray(1001)
    var primeCnt = 0
    private fun primes(n: Int) {
        run {
            var i = 2
            while (i * i <= n) {
                var j = i * i
                while (j <= n) {
                    if (!isPrime[j]) isPrime[j] = true
                    j += i
                }
                i++
            }
        }

        for (i in 2..n) if (!isPrime[i]) primeList[primeCnt++] = i
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val input = Scanner(System.`in`)
        val t = input.nextInt()
        val testCase = IntArray(t)
        var max = 0
        for (i in 0 until t) {
            testCase[i] = input.nextInt()
            if (testCase[i] > max) max = testCase[i]
        }
        input.close()

        primes(max)
        for (i in 0 until t) {
            var cnt = 0

            if (!isPrime[testCase[i] - 4]) {
                cnt++
                println(2.toString() + " " + 2 + " " + (testCase[i] - 4))
                continue
            }

            outerloop@ for (j in 1 until primeCnt) {
                for (k in 1 until primeCnt) {
                    for (u in 1 until primeCnt) {
                        if (primeList[j] + primeList[k] + primeList[u] == testCase[i]) {
                            cnt++
                            println(primeList[j].toString() + " " + primeList[k] + " " + primeList[u])
                            break@outerloop
                        }
                    }
                }
            }

            if (cnt == 0) println(0)
        }
    }
}