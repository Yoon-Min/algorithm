import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.*

fun main() {
    val n = readln().toInt()
    for(length in n downTo 1) {
        println(List(length) { "*" }.joinToString(""))
    }
}