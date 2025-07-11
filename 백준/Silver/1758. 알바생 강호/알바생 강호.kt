import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    var sum = 0L
    val n = readLine().toInt()
    val arr = List(n) { readLine().toInt() }.sortedDescending().forEachIndexed { i, e ->
        sum += if(e - i >= 0) e-i else 0
    }
    println(sum)
}