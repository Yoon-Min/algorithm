import kotlin.math.*

val dVector = listOf(Pair(0,1), Pair(1,0), Pair(0,-1), Pair(-1,0))
val virusPos = mutableListOf<Pair<Int,Int>>()
var minTime = Int.MAX_VALUE
val map = mutableListOf<List<Int>>()

fun main() = with(System.`in`.bufferedReader()) {
    val (n,m) = readLine().split(" ").map { it.toInt() }
    repeat(n) {
        map.add(readLine().split(" ").map { it.toInt() })
    }

    for(i in 0 until n) {
        for(j in 0 until n) {
            if(map[i][j] == 2) {
                virusPos.add(Pair(i,j))
            }
        }
    }
    repeat(m) {
        val maxSize = it+1
        createComb(0, maxSize)
    }
    if(minTime == Int.MAX_VALUE) println(-1)
    else println(minTime)
}

fun createComb(s: Int, maxSize: Int, tmp: MutableList<Int> = mutableListOf()) {
    if(tmp.size == maxSize) {
        var maxTime = 0
        val cloneMap = map.map { it.toMutableList() }
        val q = ArrayDeque<Triple<Int,Int,Int>>()
        var empty = map.sumOf { it.count { block -> block != 1 } } - maxSize

        tmp.map { virusPos[it] }.forEach { q.add(Triple(it.first,it.second,0)) }
        tmp.toSet().also { set ->
            virusPos.indices.forEach { i ->
                if(!set.contains(i)) {
                    val pos = virusPos[i]
                    cloneMap[pos.first][pos.second] = 0
                }
            }
        }

        while(q.isNotEmpty() && empty > 0) {
            val cur = q.removeFirst()
            dVector.forEach { v ->
                val nx = v.second + cur.second
                val ny = v.first + cur.first
                if(nx in map.indices && ny in map.indices && cloneMap[ny][nx] == 0) {
                    cloneMap[ny][nx] = cur.third+1
                    q.add(Triple(ny,nx,cur.third+1))
                    maxTime = max(maxTime, cur.third+1)
                    empty -= 1
                }
            }
        }
        if(empty == 0) {
            minTime = min(minTime, maxTime)
        }
        return
    }
    for(i in s..virusPos.lastIndex) {
        tmp.add(i)
        createComb(i+1, maxSize, tmp)
        tmp.removeLast()
    }
}

