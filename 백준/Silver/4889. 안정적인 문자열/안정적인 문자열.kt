import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    var seq = 1
    while(true) {
        val inp = readLine()

        if (inp.first() == '-') {
            break
        }
        if (inp.isEmpty()) {
            println("${seq++}. 0")
            continue
        }

        var counter = 0
        val openBracket = mutableListOf<Char>()

        inp.forEach { c ->
            if (c == '{') {
                openBracket.add(c)
            }
            else if (openBracket.isNotEmpty()) {
                openBracket.removeLast()
            }
            else {
                counter += 1
                openBracket.add('{')
            }
        }
        counter += openBracket.size / 2
        println("${seq++}. $counter")
    }
}


