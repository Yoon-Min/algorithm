import kotlin.math.*

fun main() {
    val n = readln().toInt()
    val sizeRequest = readln().split(" ").map { it.toInt() }
    val (t, p) = readln().split(" ").map { it.toInt() }
    println(sizeRequest.sumOf { ceil(it.toDouble()/t) }.toInt())
    println("${n/p} ${n%p}")
}



