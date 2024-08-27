import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.*

fun main() {
    val (r1, s) = readln().split(" ").map { it.toInt() }
    println(s*2 - r1)
}
