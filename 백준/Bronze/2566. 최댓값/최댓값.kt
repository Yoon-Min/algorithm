import java.util.*

fun main() {
    val arr = MutableList(9) { mutableListOf<Int>() }
    repeat(9) {
        arr[it] += readln().split(" ").map { e -> e.toInt() }
    }
    arr.mapIndexed { i, c -> c.mapIndexed { j, r -> Triple(r, i, j) } }
        .map { it -> it.maxBy { e -> e.first } }
        .maxBy { it.first }
        .also {
            println(it.first)
            println("${it.second+1} ${it.third+1}")
        }
}