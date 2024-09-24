import kotlin.math.*

fun main() {
    var sum  = 0
    repeat(4) {
        sum += readln().toInt()
    }
    val minutes = sum / 60
    val second = sum % 60
    println(minutes)
    println(second)
}