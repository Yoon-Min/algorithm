import kotlin.math.*
import kotlin.system.exitProcess

val ladderMap = List(31) { MutableList(11) { 0 } }
var n = 0
var m = 0
var h = 0

fun main() {
    val sizeInfo = readln().split(" ").map { it.toInt() }
    n = sizeInfo[0]
    m = sizeInfo[1]
    h = sizeInfo[2]

    if (m == 0) {
        println(0)
        return
    }

    repeat(m) {
        val (a, b) = readln().split(" ").map { it.toInt() }
        ladderMap[a][b] = 1
    }

    for (maxSize in 0..3) {
        dfs(maxSize)
    }
    println(-1)
}

fun dfs(
    totalSize: Int,
    count: Int = 0
) {
    if (totalSize == count) {
        if(checkLadderMap()) {
            println(totalSize)
            exitProcess(0)
        }
        return
    }

    for(i in 1..h) {
        for(j in 1..< n) {
            if(ladderMap[i][j] == 0 && ladderMap[i][j-1] == 0 && ladderMap[i][j+1] == 0) {
                ladderMap[i][j] = 1
                dfs(totalSize,count+1)
                ladderMap[i][j] = 0
            }
        }
    }
}

fun checkLadderMap(): Boolean {
    for (lineNumber in 1..n) {
        var curLine = lineNumber
        for (curH in 1..h) {
            val rightLadder = ladderMap[curH][curLine]
            val leftLadder = if (curLine > 1) ladderMap[curH][curLine - 1] else 0
            if (rightLadder == 1) curLine += 1
            else if (leftLadder == 1) curLine -= 1
        }
        if (curLine != lineNumber) return false
    }
    return true
}



