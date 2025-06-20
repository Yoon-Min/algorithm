import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n,l) = readLine().split(" ").map { it.toInt() }
    val leakedPos = readLine().split(" ").map { it.toInt() }.sorted()

    val s = leakedPos.first() - 0.5
    var e = s + l
    var result = 1

    leakedPos.forEach { p ->
        if(p > e) {
            e = p - 0.5 + l
            result += 1
        }
    }

    println(result)
}