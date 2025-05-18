import kotlin.math.*

val per = mutableListOf<String>()

fun main() = with(System.`in`.bufferedReader()) {
    createPermutation()

    repeat(readLine().toInt()) {
        val candidates = mutableListOf<String>()
        val (expectedAnswer, s, b) = readLine().split(" ")

        for(candidate in per) {
            var strike = 0
            var ball = 0

            for(i in 0 until 3) {
                if(candidate[i].int() == expectedAnswer[i].int()) {
                    strike += 1
                }
                else if(expectedAnswer[i] in candidate) {
                    ball += 1
                }
            }

            if(s.toInt() == strike && b.toInt() == ball) {
                candidates.add(candidate)
            }
        }

        per.clear()
        per.addAll(candidates.toList())
    }

    println(per.size)
}

fun createPermutation(
    tmp: MutableList<Int> = mutableListOf(),
    visited: MutableList<Boolean> = MutableList(10) {false}
) {
    if(tmp.size == 3) {
        per.add(tmp.joinToString(""))
        return
    }
    for(i in 1..9) {
        if(!visited[i]) {
            tmp.add(i)
            visited[i] = true
            createPermutation(tmp,visited)
            visited[i] = false
            tmp.removeLast()
        }
    }
}

fun Char.int(): Int {
    return this.code - 48
}