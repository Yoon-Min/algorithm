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
    val word = readln()
    val counter = MutableList(26) { 0 }
    word.forEach { a ->
        counter[a.code - 97]++
    }
    println(counter.joinToString(" "))
}