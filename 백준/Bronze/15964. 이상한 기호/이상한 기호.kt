import kotlin.math.*

fun main() {
    val (a, b) = readln().split(" ").map { it.toLong() }
    println((a+b)*(a-b))
}
