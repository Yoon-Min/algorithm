import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n,m) = readLine().split(" ").map { it.toInt() }
    val arr = MutableList(n) { readLine().toInt() }.sorted()
    var result = Int.MAX_VALUE

    for(i in 0 until n) {
        var left = i+1
        var right = n-1
        while(left <= right) {
            val mid = (left+right) / 2
            if(abs(arr[mid] - arr[i]) < m) {
                left = mid+1
            }
            else {
                result = min(result, abs(arr[mid] - arr[i]))
                right = mid-1
            }
        }
    }
    println(result)
}
