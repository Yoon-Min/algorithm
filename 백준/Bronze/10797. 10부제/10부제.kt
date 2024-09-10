import kotlin.math.*

fun main() {
    val day = readln().toInt()
    val cars = readln().split(" ").map { it.toInt() }
    println(cars.count { it == day })
}
