import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    repeat(readLine().toInt()) {
        val n = readLine().toInt()
        val arr = readLine().split(" ").map { it.toInt() }
        var s = 0
        var e = arr.lastIndex
        var sum = 0L

        val dp = MutableList(n+1) {0}
        val maxUntil = MutableList(n) { Pair(arr.last(), arr.lastIndex) }

        for(i in arr.indices) {
            dp[i+1] = arr[i] + dp[i]
        }

        for(i in arr.lastIndex-1 downTo 0) {
            if(maxUntil[i+1].first < arr[i]) {
                maxUntil[i] = Pair(arr[i],i)
            }
            else if(maxUntil[i+1].first == arr[i]) {
                maxUntil[i] = maxUntil[i+1].copy(second = i)
            }
            else {
                maxUntil[i] = maxUntil[i+1]
            }
        }

        while(s <= arr.lastIndex) {
            val maxIndex = maxUntil[s].second
            val max = maxUntil[s].first
            val tmpEnd = maxIndex - 1

            if(s <= tmpEnd) {
                sum += max.toLong() * (tmpEnd-s+1) - (dp[tmpEnd+1] - dp[s])
            }

            s = maxIndex+1
        }

        println(sum)
    }
}
