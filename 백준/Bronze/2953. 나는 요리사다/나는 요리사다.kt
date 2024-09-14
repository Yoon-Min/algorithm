import kotlin.math.*

fun main() {
    val result = mutableListOf(0,0,0,0,0,0)
    repeat(5) { i ->
        val score = readln().split(" ").sumOf { it.toInt() }
        result[i+1] = score
    }
    result
        .mapIndexed { i, score -> Pair(i, score) }
        .maxBy { it.second }
        .also {
            println("${it.first} ${it.second}")
        }
}