import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.*

val br = BufferedReader(InputStreamReader(System.`in`))

fun main() {
    val n = readln().toInt()
    val lines = mutableListOf<String>()
    val height = 2*n - 1
    for(i in 1..n) {
        val blanks = List((height - (2*i - 1))/2) { " " }
        val stars = List(2*i -1) { "*" }
        lines.add((blanks + stars).joinToString(""))
    }
    lines.reversed().toMutableList().forEach { println(it) }
    lines.forEachIndexed { i, s -> if(i > 0) println(s) }
}
