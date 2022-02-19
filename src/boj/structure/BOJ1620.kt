package boj.structure

// [나는야 포켓몬 마스터 이다솜] https://www.acmicpc.net/problem/1620
fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val pokemons = mutableMapOf<String, Int>()
    repeat(n) {
        pokemons[readLine()!!] = it
    }
    val pokemonsOrder = pokemons.keys.toList()
    repeat(m) {
        val question = readLine()!!
        try {
            println(pokemonsOrder[question.toInt() - 1])
        } catch (e: NumberFormatException) {
            println(pokemons[question]!! + 1)
        }
    }
}