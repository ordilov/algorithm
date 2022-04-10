package boj.bfs

// [촌수계산] https://www.acmicpc.net/problem/2644
fun main(){
    val n = readLine()!!.toInt()
    val (a, b) = readLine()!!.split(" ").map { it.toInt() }
    val m = readLine()!!.toInt()
    val relations = Array(n + 1){ mutableListOf<Int>() }
    repeat(m){
        val (x, y) = readLine()!!.split(" ").map { it.toInt() }
        relations[x].add(y)
        relations[y].add(x)
    }
    var answer = -1
    val dist = IntArray(n + 1){ Int.MAX_VALUE }
    val queue = ArrayDeque<Int>()
    queue.add(a)
    dist[a] = 0
    while(queue.isNotEmpty()){
        val current = queue.removeFirst()
        if(current == b){
            answer = dist[b]
            break
        }
        for(next in relations[current]){
            if(dist[next] > dist[current] + 1){
                dist[next] = dist[current] + 1
                queue.add(next)
            }
        }
    }

    println(answer)
}