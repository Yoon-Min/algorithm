import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.*

fun main() {
    val n = readln().toInt()
    val size = 2*n-1
    val line = MutableList(size) {""}
    for(col in 1..n) {
        val lineString = List(col) { "*" }.joinToString("")
        line[col-1] = lineString
        line[size - col] = lineString
    }
    line.forEach {
        println(it)
    }
}