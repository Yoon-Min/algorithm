import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    repeat(readLine().toInt()) {
        println(readLine().split(" ").sumOf { it.toInt() })
    }
}
