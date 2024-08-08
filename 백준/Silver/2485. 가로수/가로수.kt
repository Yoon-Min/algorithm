import java.util.*
import kotlin.math.*

fun gcd(a: Int, b: Int): Int {
    if(a*b == 0) return a+b
    return if(a > b) { gcd(a%b, b) }
    else { gcd(a, b%a) }
}

fun main() {
    val pos = mutableListOf<Int>()
    val dist = mutableListOf<Int>()
    var sum = 0

    repeat(readln().toInt()) {
        pos.add(readln().toInt())
    }
    pos.sort()
    for(i in 1..pos.lastIndex) {
        dist.add(pos[i] - pos[i-1])
    }
    var gcd = dist.first()
    dist.forEach {
        gcd = gcd(gcd, it)
    }
    dist.forEach {
        sum += (it/gcd - 1)
    }
    println(sum)
}