import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val line = List(n) { readLine().toInt() }.sortedDescending()
    var maxSum = -1

    var i = 0
    while(i < n) {
        if (i+2 < n) {
            val longestLine = line[i]
            val otherSum = line[i+1] + line[i+2]
            if (longestLine < otherSum) {
                maxSum = longestLine + otherSum
                break
            }
        }
        else break
        i += 1
    }
    println(maxSum)
}