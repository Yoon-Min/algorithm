import java.util.*
import kotlin.math.*

val dVector = listOf(Pair(0,1), Pair(1,0), Pair(0,-1), Pair(-1,0))

fun main() = with(System.`in`.bufferedReader()) {
    val (n, k) = readLine().split(" ").map { it.toInt() }
    val map = List(n) { readLine().split(" ").map { it.toInt() }.toMutableList() }
    val (s, x, y) = readLine().split(" ").map { it.toInt() }
    val virusPos = List(k+1) { mutableListOf<Pair<Int,Int>>() }.also {
        for(i in map.indices) {
            for(j in map.indices) {
                if(map[i][j] > 0) it[map[i][j]].add(Pair(i,j))
            }
        }
    }

    for(time in 1..s) {
        for(curK in 1..k) {
            val tmpStack = mutableListOf<Pair<Int,Int>>()
            virusPos[curK].forEach { p ->
                dVector.forEach { v ->
                    val nx = p.second + v.second
                    val ny = p.first + v.first
                    if(nx in map.indices && ny in map.indices && map[ny][nx] == 0) {
                        map[ny][nx] = map[p.first][p.second]
                        tmpStack.add(Pair(ny,nx))
                    }
                }
            }
            virusPos[curK].clear()
            tmpStack.forEach { virusPos[curK].add(it) }
        }
    }
    println(map[x-1][y-1])
}