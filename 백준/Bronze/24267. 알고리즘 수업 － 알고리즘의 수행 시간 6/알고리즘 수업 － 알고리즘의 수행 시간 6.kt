import java.util.*
import kotlin.math.*

fun main() {
    val n = readln().toLong()
    var sum = 0L
    var result = 0L
    for(i in 1..n-2) {
        sum += i
        result += sum
    }
    println(result)
    println(3)
}