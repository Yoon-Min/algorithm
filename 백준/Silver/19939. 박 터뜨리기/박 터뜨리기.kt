import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n,k) = readLine().split(" ").map { it.toInt() }

    if (n < k) {
        println(-1)
        return
    }

    val b = k * (k+1) / 2 - k
    if (n-b < k) {
        println(-1)
        return
    }
    val min = (n - b) / k
    val max = (min + k-1) + if((n-b) % k > 0) 1 else 0
//    (min..max).forEach { print("$it ")}
//    println()
    println(max - min)
}