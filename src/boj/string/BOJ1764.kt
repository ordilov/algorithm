package boj.string

import java.util.TreeSet

//[듣보잡] https://www.acmicpc.net/problem/1764
fun main(){
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val ns = TreeSet<String>()
    val ms = TreeSet<String>()
    repeat(n){
        val s = readLine()!!
        ns.add(s)
    }
    repeat(m){
        val s = readLine()!!
        ms.add(s)
    }
    println(ns.intersect(ms).size)
    ns.intersect(ms).forEach { println(it) }
}