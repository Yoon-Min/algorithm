import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val a = readLine().split(" ").map { it.toInt() }.sorted()
    val b = readLine().split(" ").map { it.toInt() }.sortedDescending()

    val result = a.zip(b).sumOf { it.first * it.second }

    println(result)
}