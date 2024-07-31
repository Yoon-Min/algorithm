import java.util.*
import kotlin.math.*

fun main() {
    val points = mutableListOf<Pair<Int,Int>>()
    repeat(readln().toInt()) {
        val (x, y) = readln().split(" ").map { it.toInt() }
        points.add(Pair(x, y))
    }

    val width = points.maxBy { it.first }.first - points.minBy { it.first }.first
    val height = points.maxBy { it.second }.second - points.minBy { it.second }.second
    println(width * height)
}
