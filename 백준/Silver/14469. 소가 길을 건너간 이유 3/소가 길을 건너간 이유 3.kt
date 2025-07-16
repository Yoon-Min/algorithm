import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val cowLog = List(n) { readLine().split(" ").map { it.toInt() } }.sortedBy { it.first() }
    var passedTime = cowLog.first().first()

    cowLog.forEach { log ->
        if (log.first() > passedTime) {
            passedTime = log.first()
        }
        passedTime += log.last()
    }
    println(passedTime)
}