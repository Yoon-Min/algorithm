import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.*

fun main() {
    val n = readln().toInt()
    val maxLength = 2*n - 1
    val blankSize = List(n) { maxLength - (2*(it+1) - 1) }
    (1..n).forEach { i ->
        val sideBlankSize = blankSize[i-1] / 2
        val line = List(sideBlankSize) { " " } + List(2*i - 1) { "*" }
        println(line.joinToString(""))
    }
}
