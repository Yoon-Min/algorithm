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
    val (n, m) = readln().split(" ").map { it.toLong() }
    println(abs(n-m))
}