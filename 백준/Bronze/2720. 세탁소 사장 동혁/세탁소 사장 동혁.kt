import java.util.*
import kotlin.math.*

fun main() {
    val u = listOf(25, 10, 5, 1)
    val result = mutableListOf(0, 0, 0, 0)
    repeat(readln().toInt()) {
        var c = readln().toInt()
        var ui = 0
        while(c > 0) {
            result[ui] = c/u[ui]
            c %= u[ui]
            ui++
        }
        println(result.joinToString(" "))
        result.fill(0)
    }
}
