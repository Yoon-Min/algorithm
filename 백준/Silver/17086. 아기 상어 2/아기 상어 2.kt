import kotlin.math.*

val dVector =
    listOf(Pair(0, 1), Pair(1, 0), Pair(0, -1), Pair(-1, 0), Pair(1, 1), Pair(-1, -1), Pair(-1, 1), Pair(1, -1))

fun main() = with(System.`in`.bufferedReader()) {
    var safetyDistance = 0
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val set = mutableSetOf<Pair<Int,Int>>()
    val map = List(n) { i ->
        readLine().split(" ").map { it.toInt() }.also {
            for(j in it.indices) {
                if(it[j] == 1) { set.add(Pair(i,j)) }
            }
        }
    }
    for (i in 0 until n) {
        for (j in 0 until m) {
            if(map[i][j] == 1) continue
            val q = ArrayDeque<Triple<Int,Int,Int>>().also { it.add(Triple(i,j,0))}
            val isVisited = List(n) { MutableList(m) {false} }.also { it[i][j] = true }
            while(q.isNotEmpty()) {
                val cur = q.removeFirst()
                if(map[cur.first][cur.second] == 1) {
                    safetyDistance = max(safetyDistance, cur.third)
                    break
                }
                for(v in dVector) {
                    val nx = v.second + cur.second
                    val ny = v.first + cur.first
                    if(nx in 0 until m && ny in 0 until n && !isVisited[ny][nx]) {
                        isVisited[ny][nx] = true
                        q.add(Triple(ny,nx,cur.third+1))
                    }
                }
            }
        }
    }
    println(safetyDistance)
}

