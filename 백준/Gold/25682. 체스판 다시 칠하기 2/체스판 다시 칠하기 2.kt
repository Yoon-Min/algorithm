import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.*

val bw = BufferedWriter(OutputStreamWriter(System.out))
val br = BufferedReader(InputStreamReader(System.`in`))

fun main() {
    val (n, m, k) = readln().split(" ").map { it.toInt() }
    val inputMap = mutableListOf<List<Int>>()
    val blackStartMap = List(n) { i ->
        List(m) { j ->
            if (i % 2 == 0 && j % 2 == 0) 1
            else if (i % 2 == 1 && j % 2 == 1) 1
            else 0
        }
    }
    val whiteStartMap = blackStartMap.map { i -> i.map { if (it == 1) 0 else 1 } }
    val dp = MutableList(n) { MutableList(m) { 0 } }
    repeat(n) {
        inputMap += readln().map { if (it == 'B') 1 else 0 }
    }

    val blackCase = getNegativeMap(inputMap.toList(), blackStartMap)
    val whiteCase = getNegativeMap(inputMap.toList(), whiteStartMap)
//    blackCase.forEach { println(it) }
//    whiteCase.forEach { println(it) }
    val whiteCaseMin = getMinimum(MutableList(n) { MutableList(m) { 0 } }, blackCase, k)
    val blackCaseMin = getMinimum(MutableList(n) { MutableList(m) { 0 } }, whiteCase, k)
    println(min(whiteCaseMin, blackCaseMin))
}

fun getNegativeMap(a: List<List<Int>>, b: List<List<Int>>): List<List<Int>> {
    return a
        .zip(b)
        .map {
            it.first
                .zip(it.second)
                .map { p -> p.first xor p.second }
        }
}

fun setDp(dp: MutableList<MutableList<Int>>, negativeMap: List<List<Int>>) {
    for (i in 0..negativeMap.first().lastIndex) {
        val prevRowIndex = if (i == 0) 0 else i - 1
        dp[0][i] = negativeMap[0][i] + dp[0][prevRowIndex]
    }
    for (i in 1..negativeMap.lastIndex) {
        dp[i][0] = dp[i - 1][0] + negativeMap[i][0]
        for (j in 1..negativeMap.first().lastIndex) {
            dp[i][j] = negativeMap[i][j] + (dp[i - 1][j] + dp[i][j - 1]) - dp[i - 1][j - 1]
        }
    }

}

fun getMinimum(dp: MutableList<MutableList<Int>>, negativeMap: List<List<Int>>, k: Int): Int {
    setDp(dp, negativeMap)
//    dp.forEach { println(it) }
//    println()
    var result = Int.MAX_VALUE
    for (i in 0..negativeMap.lastIndex) {
        for (j in 0..negativeMap.first().lastIndex) {
            val minCol = i - (k - 1)
            val minRow = j - (k - 1)
            if (isValidScope(minCol, minRow, k)) {
                val a = if(minCol == 0) 0 else dp[minCol-1][j]
                val b = if(minRow == 0) 0 else dp[i][minRow-1]
                val c = if(a > 0 && b > 0) dp[minCol-1][minRow-1] else 0
                val cost = dp[i][j] - (a + b - c)
                result = min(cost, result)
//                println("(i, j) -> $i $j --- $b $a $c ${dp[i][j]} $cost")
            }
        }
    }
    return result
}

fun isValidScope(i: Int, j: Int, k: Int): Boolean {
    val validScope = 0..Int.MAX_VALUE
    return i in validScope && j in validScope
}

fun bufferPrint(str: String) {
    bw.write("$str\n")
}
