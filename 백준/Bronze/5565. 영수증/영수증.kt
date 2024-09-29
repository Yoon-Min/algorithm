import kotlin.math.*

fun main() {
    val totalPrice = readln().toInt()
    var sumReadablePrice = 0
    repeat(9) {
        sumReadablePrice += readln().toInt()
    }
    println(totalPrice - sumReadablePrice)
}

