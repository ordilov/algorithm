package boj.math

// [Σ] https://www.acmicpc.net/problem/13172

val mod = 1000000007
fun gcd(pa: Long, pb: Long): Long {
    var a = pa
    var b = pb
    if (a > b) a = b.also { b = a }
    while (a > 0) {
        val c = b % a
        b = a
        a = c
    }
    return b
}

fun sol(n: Int, k: Long): Long {
    if (n == 1) return k
    var ret = 1L
    if (n % 2 == 1) ret = k
    var v = sol(n / 2, k)
    v = (v * v) % mod
    return (ret * v) % mod
}

fun main() = with(System.`in`.bufferedReader()) {
    val M = readLine().toInt()
    var ans = 0L
    repeat(M) {
        val (a, b) = readLine().split(' ').map { it.toLong() }
        ans += when {
            a % b == 0L -> (a / b)
            else -> {
                val g = gcd(a, b)
                var down = (a / g)
                val up = (b / g)
                down = sol(mod - 2, down)
                down * up
            }
        }
        ans %= mod
    }
    println(ans)
}