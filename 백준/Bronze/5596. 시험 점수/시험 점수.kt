import kotlin.math.*

fun main() {
    val s = readln().split(" ").sumOf { it.toInt() }
    val t = readln().split(" ").sumOf { it.toInt() }
    if(s == t) println(s)
    else {
        println(max(s, t))
    }
}

