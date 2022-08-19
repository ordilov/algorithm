package programmers.kakaointern19

class PRO64064 {
    private val visited = mutableSetOf<MutableSet<String>>()

    fun solution(user_id: Array<String>, banned_id: Array<String>): Int {
        fun recursive(
            now: Int,
            set: MutableSet<String>
        ) {
            if (now != set.size) {
                return
            }

            if (now == banned_id.size) {
                visited.add(set)
                return
            }

            val ban = Regex(banned_id[now].replace("*", "."))
            for (id in user_id) {
                if (id.length != ban.pattern.length || !id.matches(ban)) continue
                recursive(now + 1, set.plus(id).toMutableSet())
            }
        }
        recursive(0, mutableSetOf())
        return visited.size
    }
}

fun main() {
    print(
        PRO64064().solution(
            arrayOf("frodo", "fradi", "crodo", "abc123", "frodoc"),
            arrayOf("*rodo", "*rodo", "******")
        )
    )
}