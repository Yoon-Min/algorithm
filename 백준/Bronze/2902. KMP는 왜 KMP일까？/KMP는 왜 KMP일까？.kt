import kotlin.math.*

fun main() {
    val longVersion = readln()
    val shortVersion = longVersion.split('-').joinToString("") { it.first().toString() }
    println(shortVersion)
}

