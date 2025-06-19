import kotlin.math.*

val dVector = listOf(Pair(1, 0), Pair(0, 1), Pair(-1, 0), Pair(0, -1))

fun main() = with(System.`in`.bufferedReader()) {
    repeat(readLine().toInt()) {
        val seq = readLine()
        var curX = 0
        var curY = 0
        var curDir = 0

        var minX = 0
        var maxX = 0
        var minY = 0
        var maxY = 0

        seq.forEach { curSeq ->
            when (curSeq) {
                'L' -> {
                    curDir = getRotatedDirectionByLeft(curDir)
                }
                'R' -> {
                    curDir = getRotatedDirectionByRight(curDir)
                }
            }

            if (curSeq == 'F') {
                curX += dVector[curDir].second
                curY += dVector[curDir].first
            }
            else if(curSeq == 'B') {
                val nextVector = getRotatedDirectionByBack(curDir)
                curX += dVector[nextVector].second
                curY += dVector[nextVector].first
            }

            minX = min(minX, curX)
            maxX = max(maxX, curX)
            minY = min(minY, curY)
            maxY = max(maxY, curY)
        }
        println((maxX - minX) * (maxY - minY))
    }
}

fun getRotatedDirectionByLeft(curDir: Int): Int {
    return when (curDir) {
        0 -> 3
        1 -> 0
        2 -> 1
        else -> 2
    }
}

fun getRotatedDirectionByRight(curDir: Int): Int {
    return when (curDir) {
        0 -> 1
        1 -> 2
        2 -> 3
        else -> 0
    }
}

fun getRotatedDirectionByBack(curDir: Int): Int {
    return when (curDir) {
        0 -> 2
        1 -> 3
        2 -> 0
        else -> 1
    }
}