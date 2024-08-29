import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.*

val combinations = mutableListOf<List<Int>>()
val map = mutableListOf<List<Int>>()
val emptyList = mutableListOf<Pair<Int, Int>>()
val virusList = mutableListOf<Pair<Int, Int>>()

fun main() {
    val n = readln().toInt()
    val lineMaxLength = 2*n
    val result = MutableList(2*n - 1) {""}
    for(lineNumber in 1 .. n) {
        val starSize = lineNumber * 2
        val blankSize = lineMaxLength - starSize
        val stars = MutableList(starSize/2) { "*" }
        val line = (stars + MutableList(blankSize) { " " } + stars).joinToString("")
        val topIndex = lineNumber - 1
        val bottomIndex = (2*n - 1) - (topIndex + 1)
        result[topIndex] = line
        result[bottomIndex] = line
    }
    result.forEach { println(it) }
}