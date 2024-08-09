import java.util.*
import kotlin.math.*

fun main() {
    repeat(readln().toInt()) {
        var n = readln().toLong()
        when (n) {
            in (0..1) -> println(2)
            in (2..3) -> println(n)
            else -> {
                while(!isPrime(n)) { n++ }
                println(n)
            }
        }
    }
}

fun isPrime(n: Long): Boolean {
    for(i in 2..sqrt(n.toDouble()).toLong()) {
        if(n%i == 0L) return false
    }
    return true
}