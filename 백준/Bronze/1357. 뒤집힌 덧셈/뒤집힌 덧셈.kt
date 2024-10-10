import kotlin.math.*

fun main() {
    val (a,b) = readln().split(" ").map { it.toInt() }
    println(rev(rev(a) + rev(b)))
}

fun rev(n: Int): Int {
    return n.toString().reversed().toInt()
}

