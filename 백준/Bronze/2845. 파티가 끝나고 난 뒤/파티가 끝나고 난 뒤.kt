import kotlin.math.*

val curDice = mutableListOf(0,0,0,0,0,0)

fun main() {
    val (l, p) = readln().split(" ").map { it.toInt() }
    val reportedCount = readln().split(" ").map { it.toInt() }
    val result = reportedCount.map { it - l*p }.joinToString(" ")
    println(result)
}