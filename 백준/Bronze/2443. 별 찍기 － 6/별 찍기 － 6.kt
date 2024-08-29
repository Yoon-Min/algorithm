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
    val lineMaxLength = 2*n - 1
    for(lineNumber in n downTo 1) {
        val starSize = 2*lineNumber-1
        val blankSize = (lineMaxLength - starSize) / 2
        val stars = MutableList(starSize) { "*" }
        val blanks = MutableList(blankSize) { " " }
        println((blanks + stars).joinToString(""))
    }
}