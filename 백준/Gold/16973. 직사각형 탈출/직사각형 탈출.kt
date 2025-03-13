import kotlin.math.*
import kotlin.system.exitProcess

val dVector = listOf(Pair(0,1), Pair(1,0), Pair(0,-1), Pair(-1,0))

fun main() = with(System.`in`.bufferedReader()) {
    val (n,m) = readLine().split(" ").map { it.toInt() }
    val map = List(n) { readLine().split(" ").map { it.toInt() } }
    val square = readLine().split(" ").map { it.toInt() }.run {
        Square(this[0], this[1], this[2], this[3], this[4], this[5])
    }
    val q = ArrayDeque<Triple<Int,Int,Int>>().also { it.add(Triple(square.sr-1, square.sc-1, 0)) }
    val isVisited = List(n) { MutableList(m) { false } }.also { it[square.sr-1][square.sc-1] = true }
    val s = mutableSetOf<Pair<Int,Int>>().also {
        for(i in 0 until n) {
            for(j in 0 until m) {
                if(map[i][j] == 1) it.add(Pair(i,j))
            }
        }
    }

    while(q.isNotEmpty()) {
        val cur = q.removeFirst()
        val curR = cur.first
        val curC = cur.second
        val curDepth = cur.third
        if(curR == square.fr-1 && curC == square.fc-1) {
            println(curDepth)
            exitProcess(0)
        }
        dVector.forEach { v ->
            val nx = v.second + curC
            val ny = v.first + curR
            if(nx in 0 .. (m - square.w) && ny in 0 .. (n - square.h) && map[ny][nx] == 0 && !isVisited[ny][nx]) {
                val nextWidthRange = nx until nx+square.w
                val nextHeightRange = ny until ny+square.h
                var isPossibleToMove = true
                for(wall in s) {
                    if(wall.first in nextHeightRange && wall.second in nextWidthRange) {
                        isPossibleToMove = false
                        break
                    }
                }
                if(isPossibleToMove) {
                    q.add(Triple(ny,nx,curDepth+1))
                    isVisited[ny][nx] = true
                }
            }
        }
    }
    println(-1)
}

data class Square(
    val h: Int,
    val w: Int,
    val sr: Int,
    val sc: Int,
    val fr: Int,
    val fc: Int
)

