package boj.dp

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.max

//[게임 개발] https://www.acmicpc.net/problem/1516
object Main {
    private lateinit var indegree: IntArray
    private var N: Int = 0

    @JvmStatic
    fun main(args: Array<String>) {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.out))
        var st: StringTokenizer
        N = br.readLine().toInt()
        val a = ArrayList<ArrayList<Int>>(N)
        for (i in 0..N) {
            a.add(ArrayList())
        }
        indegree = IntArray(N + 1)
        val times = IntArray(N + 1)
        for (i in 1..N) {
            st = StringTokenizer(br.readLine())
            times[i] = st.nextToken().toInt()
            while (true) {
                val num = st.nextToken().toInt()
                if (num == -1) {
                    break
                }
                a[num].add(i)
                indegree[i]++
            }
        }
        val ans = topologicalSort(a, times)
        bw.write(ans)
        bw.flush()
        bw.close()
        br.close()
    }

    private fun topologicalSort(
        a: ArrayList<ArrayList<Int>>,
        times: IntArray,
    ): String {
        val q: Queue<Int> = LinkedList()
        val sb = StringBuilder()

        for (i in 1..N) {
            if (indegree[i] == 0) {
                q.offer(i)
            }
        }

        val result = IntArray(N + 1)
        while (!q.isEmpty()) {
            val now = q.poll()
            for (next in a[now]) {
                indegree[next]--

                result[next] = max(result[next], result[now] + times[now])
                if (indegree[next] == 0) {
                    q.offer(next)
                }
            }
        }

        for (i in 1..N) {
            sb.append("${result[i] + times[i]}\n")
        }
        return sb.toString()
    }
}