import java.util.*
import kotlin.math.*

fun gcd(a: Long, b: Long): Long {
    if(a*b == 0L) return a+b
    return if(a > b) { gcd(a%b, b) }
    else { gcd(a, b%a) }
}

fun main() {
    val (a, b) = readln().split(" ").map { it.toLong() }
    val gcd = gcd(a,b)
    println(gcd * ((a/gcd) * (b/gcd)))
}