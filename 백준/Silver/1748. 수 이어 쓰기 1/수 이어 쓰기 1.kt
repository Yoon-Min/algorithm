import kotlin.math.*

fun main() {
    val n = readln().toInt()
    var unit = 10
    var curLen = 1
    var len = 0
    for(i in 1..n) {
        if(i % unit == 0) {
            unit *= 10
            curLen += 1
        }
        len += curLen
    }
    println(len)
}

