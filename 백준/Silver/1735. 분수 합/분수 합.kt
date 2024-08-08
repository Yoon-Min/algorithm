import java.util.*
import kotlin.math.*

fun gcd(a: Long, b: Long): Long {
    if(a*b == 0L) return a+b
    return if(a > b) { gcd(a%b, b) }
    else { gcd(a, b%a) }
}

fun main() {
    val arr = mutableListOf<List<Long>>()
    repeat(2) {
        arr.add(readln().split(" ").map { it.toLong() })
    }
    val top = arr[0][0] * arr[1][1] + arr[1][0] * arr[0][1]
    val bottom = arr[0][1] * arr[1][1]
    val gcd = gcd(top, bottom)
    println("${top/gcd} ${bottom/gcd}")
}