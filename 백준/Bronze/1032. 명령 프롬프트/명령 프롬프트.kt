import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.*

fun main() {
    val n = readln().toInt()
    val arr = MutableList<MutableSet<Char>>(50) { mutableSetOf() }
    repeat(n) {
        readln().forEachIndexed { i, c ->
            arr[i].add(c)
        }
    }
    val result = arr.filter { it.size > 0 }.map { if(it.size > 1) '?' else it.first() }.joinToString("")
    println(result)
}

