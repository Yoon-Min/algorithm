import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n,k) = readLine().split(" ").map { it.toInt() }
    val arr = readLine().split(" ").map { it.toInt() }

    var left = 0
    var right = 0
    var oddCounter = if(arr[left].isOdd()) 1 else 0
    var maxLength = 0

    while(right < n) {

        val curLength = right - left + 1

        if(oddCounter > k) {
            if(arr[left].isOdd()) {
                oddCounter -= 1
            }
            left += 1
        }
        else {
            maxLength = max(maxLength, curLength - oddCounter)
            right += 1
            if(right < n && arr[right].isOdd()) {
                oddCounter += 1
            }
        }
    }

    println(maxLength)
}

fun Int.isOdd(): Boolean {
    return this % 2 == 1
}