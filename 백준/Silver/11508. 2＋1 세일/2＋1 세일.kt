import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val cost = List(n) { readLine().toInt() }.sortedDescending()

    var remainedN = n
    var minCost = 0L
    var i = 0
    while(remainedN > 0) {
        if (remainedN > 2) {
            (i..i+1).forEach { j ->
                minCost += cost[j]
            }
            i += 3
            remainedN -= 3
        }
        else {
            (i..i+(remainedN-1)).forEach { j ->
                minCost += cost[j]
            }
            remainedN = 0
        }
    }
    println(minCost)
}