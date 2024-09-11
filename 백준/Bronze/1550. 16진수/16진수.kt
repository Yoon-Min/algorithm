import kotlin.math.*

fun main() {
    val hex = readln().reversed()
    var sum = 0L
    for(i in 0..hex.lastIndex) {
        val a = 16.0.pow(i).toLong()
        val b = when(val c = hex[i].code) {
            in 65..90 -> c - 55
            else -> c - 48
        }
        sum += a*b
    }
    println(sum)
}
