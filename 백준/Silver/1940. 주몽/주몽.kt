import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val m = readLine().toInt()
    val arr = readLine().split(" ").map { it.toInt() }.sorted()

    var left = 0
    var right = arr.lastIndex
    var pairCount = 0

    while(left < right) {
        val sum = arr[left] + arr[right]
        if(sum <= m) {
            if(sum == m) pairCount += 1
            left += 1
            right = arr.lastIndex
        }
        else {
            right -= 1
        }
    }
    println(pairCount)
}