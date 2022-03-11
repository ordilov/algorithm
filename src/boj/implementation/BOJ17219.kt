package boj.implementation

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// [비밀번호 찾기] https://www.acmicpc.net/problem/17219
fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))
    val sb = StringBuilder()
    val map = HashMap<String, String>()
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    repeat(n){
        val (site, password) = br.readLine().split(" ")
        map.put(site, password)
    }
    repeat(m){
        val site = br.readLine()
        sb.appendLine(map[site])
    }
    bw.write(sb.toString())
    bw.flush()
}