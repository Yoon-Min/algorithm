import java.util.*
import kotlin.math.*

fun main() {
    val (n, b) = readln().split(" ").map { it.toLong() }
    var result = ""
    var cur = n
    while(cur >= b) {
        result = toFormatB((cur%b).toInt()) + result
        cur /= b
    }
    result = toFormatB(cur.toInt()) + result
    println(result)
}

fun toFormatB(n: Int): String {
    return if((0..9).contains(n)) n.toString() else (55+n).toChar().toString()
}