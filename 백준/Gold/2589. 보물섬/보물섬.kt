import kotlin.math.*

val dVector = listOf(Pair(0,1), Pair(1,0), Pair(0,-1), Pair(-1,0))

fun main() = with(System.`in`.bufferedReader()) {
    val (n,m) = readLine().split(" ").map { it.toInt() }
    val map = List(n) { readLine().map { if(it == 'W') -1 else 0 }.toMutableList() }
    for(i in 0 until n) {
        for(j in 0 until m) {
            if(map[i][j] == -1) continue
            val q = ArrayDeque<Triple<Int,Int,Int>>().also { it.add(Triple(i,j,0)) }
            val isVisited = List(n) { MutableList(m) { false } }.also { it[i][j] = true }

            while(q.isNotEmpty()) {
                val cur = q.removeFirst()
                map[cur.first][cur.second] = max(map[cur.first][cur.second], cur.third)

                dVector.forEach { v ->
                    val nx = v.second + cur.second
                    val ny = v.first + cur.first
                    if(nx in 0 until m && ny in 0 until n && map[ny][nx] != -1 && !isVisited[ny][nx]) {
                        isVisited[ny][nx] = true
                        q.add(Triple(ny,nx,cur.third+1))
                    }
                }
            }
        }
    }
    var max = 0
    for(i in 0 until n) {
        for (j in 0 until m) {
            if(map[i][j] == -1) continue
            max = max(max, map[i][j])
        }
    }
    println(max)
}
