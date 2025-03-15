import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val input = readLine().split(" ").map { it.toInt() }
    var resultPairIndex = Pair(0,1)
    var resultSum = Int.MAX_VALUE

    for(i in input.indices) {
        val cur = input[i]
        var s = i+1
        var e = input.lastIndex
        while(s <= e) {
            val mid = (s+e)/2
            val sum = cur+input[mid]
            if(abs(sum) < resultSum) {
                resultSum = abs(sum)
                resultPairIndex = Pair(i, mid)
                if(sum == 0) break
            }
            if(sum < 0) {
                s = mid+1
            }
            else {
                e = mid-1
            }
        }
    }
    println("${input[resultPairIndex.first]} ${input[resultPairIndex.second]}")
}

