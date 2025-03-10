
import kotlin.math.*

val dVector = listOf(Pair(0,1), Pair(1,0), Pair(0,-1), Pair(-1,0))

fun main() = with(System.`in`.bufferedReader()) {
    val (n,m,t) = readLine().split(" ").map { it.toInt() }
    val map = List(n) { readLine().split(" ").map { it.toInt() } }
    val q = ArrayDeque<Node>().also { it.add(Node(0,0,0,false)) }
    val isVisited = List(n) { MutableList(m) { false } }.also { it[0][0] = true }
    var result = Int.MAX_VALUE

    while(q.isNotEmpty()) {
        val cur = q.removeFirst()
        if(cur.col == n-1 && cur.row == m-1) {
            result = min(result, cur.totalTime)
            break
        }

        dVector.forEach { v ->
            val nx = v.second + cur.row
            val ny = v.first + cur.col
            if(nx in 0 until m && ny in 0 until n && !isVisited[ny][nx] && (cur.swordActivation || map[ny][nx] != 1)) {
                isVisited[ny][nx] = true
                if(map[ny][nx] == 2) {
                    result = min(result, cur.totalTime+1 + ((m-1) - nx) + ((n-1) - ny))
                }
                else {
                    q.add(Node(ny,nx,cur.totalTime+1, cur.swordActivation))
                }

            }
        }
    }

    if(result == Int.MAX_VALUE || result > t) {
        println("Fail")
    }
    else {
        println(result)
    }
}

data class Node(
    val col: Int,
    val row: Int,
    val totalTime: Int,
    val swordActivation: Boolean
)
