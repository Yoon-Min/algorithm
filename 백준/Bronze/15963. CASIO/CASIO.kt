import kotlin.math.*

fun main() {
    val (a,b) = readln().split(" ").map { it.toLong() }
    println(if(a == b) 1 else 0)
}
