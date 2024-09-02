import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.*

fun main() {
    val arr = listOf(
        listOf("", "", "", "", "E"),
        listOf("", "", "", "A", ""),
        listOf("", "", "B", "", ""),
        listOf("", "C", "", "", ""),
        listOf("D", "", "", "", ""),
    )

    repeat(3) {
        val line = readln().split(" ").map { it.toInt() }
        val zero = line.count { it == 0 }
        val one = line.count { it == 1 }
        println(arr[zero][one])
    }
}