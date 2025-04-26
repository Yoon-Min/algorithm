import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val arr = readLine().split(" ").map { it.toInt() }.sorted()
    var resultSum = Int.MAX_VALUE
    var resultLeft = -1
    var resultRight = -1

    for(i in arr.indices) {
        var left = i+1
        var right = arr.lastIndex

        while(left <= right) {
            val mid = (left+right) / 2
            val sum = arr[i] + arr[mid]
            if(abs(sum) < abs(resultSum)) {
                resultSum = sum
                resultLeft = arr[i]
                resultRight = arr[mid]
                if(sum == 0) break
            }
            if(sum < 0) {
                left = mid + 1
            }
            else {
                right = mid - 1
            }
        }
    }

    println("$resultLeft $resultRight")
}