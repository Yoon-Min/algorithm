import java.util.*
import kotlin.math.*

fun main() {
    val (n, b) = readln().split(" ")
    val bn = b.toLong()
    var sum = 0L
    n.forEachIndexed { i, c ->
        sum += toDecimal(c).toLong() * bn.toDouble().pow(n.lastIndex - i).toLong()
    }
    println(sum)
}

fun toDecimal(c: Char): Int {
    return if((48..57).contains(c.code)) c.code - 48 else c.code - 65 + 10
}