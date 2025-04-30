import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n,k) = readLine().split(" ").map { it.toInt() }
    val arr = readLine().split(" ").map { it.toInt() }
    var result = Int.MAX_VALUE

    var count = if(arr[0] == 1) 1 else 0
    var left = 0
    var right = 0

    while(true) {
        if(count < k) {
            right += 1
            if(right == n) break
            if(arr[right] == 1) count += 1
        }
        else {
            result = min(result, right - left + 1)
            if(arr[left] == 1) count -= 1
            left += 1
        }
    }

    if(result == Int.MAX_VALUE) println(-1)
    else println(result)
}