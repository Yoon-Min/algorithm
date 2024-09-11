import kotlin.math.*

fun main() {
    val (a, b) = readln().split(" ").map { it.toInt() }
    println((a+b)*(a-b))
}
