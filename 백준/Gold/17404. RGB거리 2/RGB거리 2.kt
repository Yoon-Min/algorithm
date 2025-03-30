import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val cost = List(n + 1) { MutableList(3) { 0 } }
    var result = Int.MAX_VALUE.toLong()
    repeat(n) { i ->
        val num = i + 1
        val rgb = readLine().split(" ").map { it.toInt() }
        cost[num][0] = rgb[0]
        cost[num][1] = rgb[1]
        cost[num][2] = rgb[2]
    }
    for(i in 0 until 3) {
        val dp = List(n+1) { MutableList(3) { Int.MAX_VALUE.toLong() } }
        dp[1][i] = cost[1][i].toLong()
        for(j in 2..n) {
            dp[j][0] = cost[j][0] + min(dp[j-1][1], dp[j-1][2])
            dp[j][1] = cost[j][1] + min(dp[j-1][0], dp[j-1][2])
            dp[j][2] = cost[j][2] + min(dp[j-1][0], dp[j-1][1])
        }
        repeat(3) {
            if(it != i) {
                result = min(result, dp[n][it])
            }
        }
    }
    println(result)
}

