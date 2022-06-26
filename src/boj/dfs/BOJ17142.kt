package boj.dfs

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.min

//[연구소 3] https://www.acmicpc.net/problem/17142

internal object Main {
    private var N = 0
    private var M = 0
    lateinit var arr: Array<IntArray>
    private var dx = intArrayOf(-1, 1, 0, 0)
    private var dy = intArrayOf(0, 0, -1, 1)
    private var viruses: MutableList<Virus> = ArrayList<Virus>()
    private lateinit var active: Array<Virus?>
    private var resultMinTime = Int.Companion.MAX_VALUE
    private var originEmptySpace = 0

    @Throws(java.lang.Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val br = BufferedReader(InputStreamReader(System.`in`))
        var st: StringTokenizer

        st = StringTokenizer(br.readLine())
        N = st.nextToken().toInt()
        M = st.nextToken().toInt()
        arr = Array(N) { IntArray(N) }
        active = arrayOfNulls(M)
        for (i in 0 until N) {
            st = StringTokenizer(br.readLine())
            for (j in 0 until N) {
                arr[i][j] = st.nextToken().toInt()
                if (arr[i][j] == 0) {
                    originEmptySpace++
                } else if (arr[i][j] == 2) {
                    viruses.add(Virus(i, j, 0))
                }
            }
        }

        if (originEmptySpace == 0) {
            println(0)
        } else {
            selectVirus(0, 0)
            println(if (resultMinTime == Int.Companion.MAX_VALUE) -1 else resultMinTime)
        }
    }

    private fun selectVirus(start: Int, selectCount: Int) {
        if (selectCount == M) {
            spreadVirus(originEmptySpace)
            return
        }
        for (i in start until viruses.size) {
            active[selectCount] = viruses[i]
            selectVirus(i + 1, selectCount + 1)
        }
    }

    private fun spreadVirus(emptySpace: Int) {
        var space = emptySpace
        val q: Queue<Virus?> = LinkedList<Virus?>()
        val infected = Array(N) { BooleanArray(N) }
        for (i in 0 until M) {
            val virus = active[i]
            infected[virus!!.x][virus.y] = true
            q.add(virus)
        }
        while (!q.isEmpty()) {
            val virus = q.poll()
            for (i in 0..3) {
                val nx = virus!!.x + dx[i]
                val ny = virus.y + dy[i]
                if ((nx < 0 || nx >= N) || ny < 0 || ny >= N) continue
                if (infected[nx][ny] || arr[nx][ny] == 1) continue
                if (arr[nx][ny] == 0) {
                    space--
                }
                if (space == 0) {
                    resultMinTime = min(resultMinTime, virus.time + 1)
                    return
                }
                infected[nx][ny] = true
                q.add(Virus(nx, ny, virus.time + 1))
            }
        }
    }

    internal data class Virus(var x: Int, var y: Int, var time: Int) {
    }
}