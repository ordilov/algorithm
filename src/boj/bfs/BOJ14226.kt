package boj.bfs

import java.util.LinkedList

//[이모티콘] https://www.acmicpc.net/problem/14226
fun main() {
    val s = readLine()!!.toInt()
    val map = Array(s + 1) { IntArray(s + 1) { Int.MAX_VALUE } }
    map[1][0] = 1
    val q = LinkedList<Save>()
    q.offer(Save(1, 0, 0))
    while (q.isNotEmpty()) {
        val (screen, clip, time) = q.poll()
        if(screen == s) {
            println(time)
            break
        }

        if (screen + clip <= s && map[screen + clip][clip] > time + 1) {
            map[screen + clip][clip] = time + 1
            q.offer(Save(screen + clip, clip, time + 1))
        }

        if (clip != screen && map[screen][screen] > time + 1){
            map[screen][screen] = time + 1
            q.offer(Save(screen, screen, time + 1))
        }

        if(screen > 0 && map[screen - 1][clip] > time + 1){
            map[screen - 1][clip] = time + 1
            q.offer(Save(screen - 1, clip, time + 1))
        }
    }
}

private data class Save(val screen: Int, val clip: Int, val time: Int)