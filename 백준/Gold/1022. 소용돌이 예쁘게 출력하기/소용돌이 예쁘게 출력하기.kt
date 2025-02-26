import kotlin.math.*

val dVector = listOf(Pair(-1,0), Pair(0,-1), Pair(1,0), Pair(0,1))
var maxNum = 0

fun main() = with(System.`in`.bufferedReader()) {
    val (r1,c1,r2,c2) = readLine().split(" ").map {it.toInt()}
    val validRange = Pair((r1..r2),(c1..c2))
    val width = validRange.second.last - validRange.second.first + 1
    val height = validRange.first.last - validRange.first.first + 1
    val resultMap = List(height) { MutableList(width) {1} }
    val totalSize = width * height
    var curRange = Pair((1 downTo -1),(1 downTo -1))
    var completedCount = 0
    var nextNum = 0
    var nx = 0
    var ny = -1

    while(completedCount < totalSize) {
        curRange = Pair((curRange.first.first-1..curRange.first.last+1), (curRange.second.first-1..curRange.second.last+1))
        val curWidth = curRange.first.last - curRange.first.first + 1
        val curHeight = curRange.second.last - curRange.second.first + 1
        val curRangeSize = curWidth * curHeight
        val curRangeOfStrokeLength = if(curRangeSize == 1) 1 else curRangeSize - (curWidth-2)*(curHeight-2)
        var curDirection = 3
        var tmpCount = 0
        while(tmpCount < curRangeOfStrokeLength) {
            val nextVector = dVector[curDirection]
            while(nx + nextVector.second in curRange.second && ny + nextVector.first in curRange.first) {
                nx += nextVector.second
                ny += nextVector.first
                nextNum += 1
                if(nx in validRange.second && ny in validRange.first ) {
                    resultMap[ny - r1][nx - c1] = nextNum
                    completedCount += 1
                    maxNum = max(maxNum, nextNum)
                }
                tmpCount += 1
            }
            curDirection = (curDirection+1) % dVector.size
        }
    }
    val maxNumLength = maxNum.length()
    val sb = StringBuilder()
    resultMap.forEach { inner ->
        inner.forEach {
            repeat(maxNumLength - it.length()) {
                sb.append(" ")
            }
            sb.append("$it ")
        }
        sb.append("\n")
    }
    println(sb)
}

fun Int.length(): Int {
    var length = 0
    val mod = 10
    var cur = this
    while(cur > 0) {
        cur /= mod
        length += 1
    }
    return length
}