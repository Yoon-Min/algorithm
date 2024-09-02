import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.*

fun main() {
    println(readln().split(" ").map { it.toInt() }.sorted().joinToString(" "))
}