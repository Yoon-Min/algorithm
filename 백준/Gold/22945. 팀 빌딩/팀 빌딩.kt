import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val arr = readLine().split(" ").map { it.toInt() }

    var left = 0
    var right = n-1
    var result = 0

    while(left+1 < right) {
        result = max(result, min(arr[left],arr[right]) * (right-left-1))
        if(arr[left] < arr[right]) {
            left += 1
        }
        else {
            right -= 1
        }
    }

    println(result)
}