package boj.implementation

//[집합] https://www.acmicpc.net/problem/11723
fun main(){
    val m = readLine()!!.toInt()
    val set = mutableSetOf<Int>()
    val s = StringBuilder()
    repeat(m){
        val line = readLine()!!.split(" ")
        val op = line[0]
        var n: Int = 0
        if(line.size == 2){
            n = line[1].toInt()
        }
        when(op){
            "add" -> set.add(n)
            "remove" -> set.remove(n)
            "check" -> s.append(if(set.contains(n)) 1 else 0).append("\n")
            "toggle" -> {
                if(set.contains(n)) set.remove(n)
                else set.add(n)
            }
            "all" -> set.addAll(1..20)
            "empty" -> set.clear()
        }
    }
    println(s)
}