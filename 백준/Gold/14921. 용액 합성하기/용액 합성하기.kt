import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val arr = readLine().split(" ").map { it.toInt() }
    var result = Int.MAX_VALUE

    for(i in 0 until n) {
        var left = i+1
        var right = n-1
        var minSum = Int.MAX_VALUE

        while(left <= right) {
            val mid = (left+right) / 2
            val sum = arr[mid] + arr[i]

            if(abs(sum) < abs(minSum)) {
                minSum = sum
            }

            if(sum < 0) {
                left = mid+1
            }
            else {
                right = mid-1
            }
        }
        if(abs(minSum) < abs(result)) {
            result = minSum
        }
    }
    println(result)
}