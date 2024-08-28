import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.*

val br = BufferedReader(InputStreamReader(System.`in`))

fun main() {
    val n = readln().toInt()
    val fib = mutableListOf(0, 1)
    (2..n).forEach {
        fib.add(fib[it-1] + fib[it-2])
    }
    println(fib.last())
}
