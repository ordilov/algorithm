package boj.mst

//[상근이의 여행] https://www.acmicpc.net/problem/9372
fun main(){
    val t = readLine()!!.toInt()
    repeat(t){
        val (n, m) = readLine()!!.split(" ").map{it.toInt()}
        repeat(m){
           readLine()
        }
        println(n - 1)
    }
}
