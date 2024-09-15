import kotlin.math.*

fun main() {
    val n = readln().toInt()
    for (i in 1..n) {
        val blankSize = n - i
        val totalStarStringSize = 2 * i - 1
        val resultLine =
            (List(blankSize) { " " } + List(totalStarStringSize) { if (it % 2 == 0) "*" else " " }).joinToString("")
        println(resultLine)
    }
}