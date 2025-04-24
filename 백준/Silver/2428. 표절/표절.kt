import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val fileSize = readLine().split(" ").map { it.toInt() }.sorted()
    var count = 0L

    for(i in fileSize.indices) {
        count += binarySearch(i, fileSize) - i
    }
    println(count)
}

fun binarySearch(comparator: Int, arr: List<Int>): Int {
    var left = comparator+1
    var right = arr.lastIndex
    var result = comparator

    while(left <= right) {
        val mid = (left + right) / 2
        if(arr[comparator]*10 >= arr[mid] * 9) {
            result = mid
            left = mid+1
        }
        else {
            right = mid-1
        }
    }

    return result
}