import kotlin.math.*

fun main() {
    var max = 0
    var total = 0
    repeat(10) {
        val (a,b) = readln().split(" ").map { it.toInt() }
        total -= a
        total += b
        max = max(max, total)
    }
    println(max)
}

