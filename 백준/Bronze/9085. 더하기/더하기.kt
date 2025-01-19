import kotlin.math.*
import kotlin.system.exitProcess

fun main() {
    repeat(readln().toInt()) {
        readln()
        println(readln().split(" ").sumOf { it.toInt() })
    }
}



