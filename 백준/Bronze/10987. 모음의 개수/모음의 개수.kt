import kotlin.math.*

fun main() {
    val s = setOf('a', 'e', 'i', 'o', 'u')
    val input = readln()
    val result = input.count { s.contains(it) }
    println(result)
}

