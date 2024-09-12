import kotlin.math.*

val curDice = mutableListOf(0,0,0,0,0,0)

fun main() {
    val t = readln().toInt()
    repeat(t) {
        val sentence = readln()
        val result = sentence.split(" ").map { it.reversed() }.joinToString(" ")
        println(result)
    }
}