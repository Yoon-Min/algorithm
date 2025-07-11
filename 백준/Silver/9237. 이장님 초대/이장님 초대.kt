import kotlin.math.*

val fib = mutableListOf(0,1)

fun main() = with(System.`in`.bufferedReader()) {
    var time = 0
    val n = readLine().toInt()
    val arr = readLine().split(" ").map { it.toInt() }.sortedDescending()

    for (i in arr.indices) {
        time = max(time, (i+1 + arr[i] + 1))
    }

    println(time)

}