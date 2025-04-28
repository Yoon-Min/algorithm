import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val numCount = MutableList(100001) { 0 }
    val (n,k) = readLine().split(" ").map { it.toInt() }
    val arr = readLine().split(" ").map { it.toInt() }

    var maxLength = 0
    var left = 0
    var right = 0
    while(right < n) {
        val firstNum = arr[left]
        val lastNum = arr[right]

        if(numCount[lastNum]+1 > k) {
            numCount[firstNum] -= 1
            left += 1
        }
        else {
            numCount[lastNum] += 1
//            println("$left $right")
            maxLength = max(maxLength, right - left + 1)
            right += 1
        }
    }
    println(maxLength)
}