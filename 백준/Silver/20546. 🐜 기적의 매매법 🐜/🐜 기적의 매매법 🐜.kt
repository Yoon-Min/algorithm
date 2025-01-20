import kotlin.math.*
import kotlin.system.exitProcess

fun main() {
    val seedMoney = readln().toInt()
    val stockPrice = readln().split(" ").map { it.toInt() }
    var bnpCash = Pair(seedMoney,0)
    var timingCash = Pair(seedMoney,0)
    var continuousRiseCount = 0
    var continuousDeclineCount = 0
    var prevStockPrice = stockPrice.first()

    stockPrice.forEach { p ->
        // 주가 상승, 하락폭 카운트
        if(p > prevStockPrice) {
            continuousRiseCount += 1
            continuousDeclineCount = 0
        }
        else if(p < prevStockPrice) {
            continuousDeclineCount += 1
            continuousRiseCount = 0
        }
        else {
            continuousDeclineCount = 0
            continuousRiseCount = 0
        }

        // BNP 방법
        if(bnpCash.first >= p) {
            bnpCash = bnpCash.copy(bnpCash.first%p, bnpCash.second + bnpCash.first/p)
        }
        // TIMING 방법
        if(continuousRiseCount == 3) {
            timingCash = timingCash.copy(timingCash.first + p*timingCash.second, 0)
            continuousRiseCount = 2
        }
        else if(continuousDeclineCount == 3) {
            timingCash = timingCash.copy(timingCash.first%p, timingCash.second + timingCash.first/p)
            continuousDeclineCount = 2
        }

        prevStockPrice = p
    }
    val bnpResult = bnpCash.first + stockPrice.last() * bnpCash.second
    val timingResult = timingCash.first + stockPrice.last() * timingCash.second
    if(bnpResult == timingResult) println("SAMESAME")
    else if(bnpResult > timingResult) println("BNP")
    else println("TIMING")
}



