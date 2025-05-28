import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    var five = 0
    var result = Int.MAX_VALUE

    while(5*five <= n) {
        val tmp = (n - (5*five))
        if(tmp % 2 == 0) {
            val two = tmp / 2
            result = min(result, five + two)
        }
        five += 1
    }
    
    if(result == Int.MAX_VALUE) {
        println(-1)
    } else {
        println(result)
    }

}