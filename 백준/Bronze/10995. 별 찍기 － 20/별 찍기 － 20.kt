import kotlin.math.*

fun main() {
    val n = readln().toInt()
    repeat(n) { i ->
        val line = List(n) { if(i%2 == 0) "* " else " *" }.joinToString("")
        println(line)
    }
}


