import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n,x) = readLine().split(" ").map { it.toInt() }
    val arr = readLine().split(" ").map { it.toInt() }
    var maxSum = (0 until x).sumOf { arr[it] }
    var nextSum = maxSum
    var days = 1

    for(end in x until n) {
        nextSum = nextSum - arr[end - x] + arr[end]
        if(nextSum > maxSum) {
            maxSum = nextSum
            days = 1
        }
        else if(nextSum == maxSum) {
            days += 1
        }
    }
    if(maxSum == 0) println("SAD")
    else {
        println(maxSum)
        println(days)
    }
}