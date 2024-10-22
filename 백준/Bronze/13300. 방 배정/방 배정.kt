import kotlin.math.*

fun main() {
    val (n,k) = readln().split(" ").map { it.toInt() }
    val group = List(7) { MutableList(2) {0} }
    repeat(n) {
        val (gender, grade) = readln().split(" ").map { it.toInt() }
        group[grade][gender]++
    }
    val a = group.sumOf { ceil(it.first().toDouble()/k).toInt() }
    val b = group.sumOf { ceil(it.last().toDouble()/k).toInt() }
    println(a+b)
}

