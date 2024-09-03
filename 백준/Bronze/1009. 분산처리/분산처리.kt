import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.*

fun main() {
    repeat(readln().toInt()) {
        val (a, b) = readln().split(" ").map { it.toInt() }
        val totalData = a.toDouble().pow(b).toLong()
        val result = getPower(a%10, b)
        if(result == 0L) println(10) else println(result)
    }
}

fun getPower(n: Int, c: Int): Long {
    if(c == 1) return n % 10L
    val half = getPower(n, c/2)
    var result = (half * half) % 10
    if(c%2 != 0) result = (result * n) % 10
    return result
}