import kotlin.math.*

val dVector = listOf(Pair(0, 1), Pair(1, 0), Pair(0, -1), Pair(-1, 0))
val virusPos = mutableListOf<Pair<Int, Int>>()
val map = List(50) { MutableList(50) { -1 } }
val cloneMap = List(50) { MutableList(50) { -1 } }
var result = Int.MAX_VALUE
var mapSize = 0

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    List(n) { i ->
        readLine().split(" ").mapIndexed { j, e ->
            val num = e.toInt()
            if (num == 1) map[i][j] = -2
            else if (num == 2) {
                virusPos.add(Pair(i, j))
                map[i][j] = -3
            }
        }
    }
    mapSize = n
    for (actVirus in m downTo 1) {
        combGenerator(0, actVirus, mutableListOf())
        if(result != Int.MAX_VALUE) {
            println(result)
            return
        }
    }
    if (result == Int.MAX_VALUE) println(-1)
    else println(result)
}

fun combGenerator(s: Int, maxSize: Int, tmpStack: MutableList<Int>) {
    if (tmpStack.size == maxSize) {
        var empty = 0
        for(i in 0 until mapSize) {
            for(j in 0 until mapSize) {
                cloneMap[i][j] = map[i][j]
                if(map[i][j] == -1) empty += 1
            }
        }
        val q = ArrayDeque<Triple<Int, Int, Int>>().also {
            tmpStack.forEach { i ->
                it.add(Triple(virusPos[i].first, virusPos[i].second, 0))
                cloneMap[virusPos[i].first][virusPos[i].second] = 0
            }
        }
        var endTime = 0
        while (q.isNotEmpty()) {
            val cur = q.removeFirst()
            val curTime = cur.third

            if (empty == 0) {
                result = min(result, endTime)
                break
            }

            for (v in dVector) {
                val nx = v.second + cur.second
                val ny = v.first + cur.first
                if (
                    nx in 0 until mapSize &&
                    ny in 0 until mapSize &&
                    (cloneMap[ny][nx] == -3 || cloneMap[ny][nx] == -1)
                ) {
                    empty -= if(cloneMap[ny][nx] == -3) 0 else 1
                    val nextTime = curTime+1
                    cloneMap[ny][nx] = nextTime
                    q.add(Triple(ny,nx,nextTime))
                    endTime = max(endTime, nextTime)
                }
            }
        }
//        for(i in 0 until mapSize) {
//            println(cloneMap[i].subList(0, mapSize).joinToString(" "))
//        }
//        println()
        return
    }
    for (i in s..virusPos.lastIndex) {
        tmpStack.add(i)
        combGenerator(i + 1, maxSize, tmpStack)
        tmpStack.removeLast()
    }
}

