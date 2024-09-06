import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.*

fun main() {
    var cur = 0
    var max = 0
    repeat(4) {
        val (totalOut, totalIn) = readln().split(" ").map { it.toInt() }
        cur -= totalOut
        cur += totalIn
        max = max(max, cur)
    }
    println(max)
}

