import java.util.*
import kotlin.math.*

fun main() {
    val n = readln().toLong()
    var i = 1
    var result = 0
    while(i*i <= n) {
        i++
        result++
    }
    println(result)
}