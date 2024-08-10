import java.util.*
import kotlin.math.*

val primeCheck = MutableList(1000001) { -1 }

fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        var result = 0
        if(n == 4) { println(1) }
        else {
            for(i in 3.. ceil((n-1).toDouble()/2).toInt() step 2) {
                if(isPrime(i) && isPrime(n-i)) result++
            }
            println(result)
        }
    }
}

fun isPrime(n: Int): Boolean {
    if(primeCheck[n] == 1) return true
    if(primeCheck[n] == 0) return false
    for(i in 2..sqrt(n.toDouble()).toInt()) {
        if(n%i == 0) {
            primeCheck[n] = 0
            return false
        }
    }
    primeCheck[n] = 1
    return true
}