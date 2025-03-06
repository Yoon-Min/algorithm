import kotlin.math.*

val dVector = listOf(Pair(0,1), Pair(1,0), Pair(0,-1), Pair(-1,0))

fun main() = with(System.`in`.bufferedReader()) {
    val (n,m) = readLine().split(" ").map { it.toInt() }
    val map = List(n) { readLine() }
    var result = Int.MAX_VALUE
    for(i in 0 until n) {
        for(j in 0 until m) {
            if(map[i][j] == '2') {
                val isVisited = List(n) { MutableList(m) { false } }.also { it[i][j] = true }
                val q = ArrayDeque<Triple<Int,Int,Int>>().also { it.add(Triple(i,j,0)) }
                while(q.isNotEmpty()) {
                    val cur = q.removeFirst()
                    if(map[cur.first][cur.second].code in 51..53) {
                        result = min(result, cur.third)
                        break
                    }
                    dVector.forEach { v ->
                        val nx = v.second + cur.second
                        val ny = v.first + cur.first
                        if(nx in 0 until m && ny in 0 until n && !isVisited[ny][nx] && map[ny][nx] != '1') {
                            isVisited[ny][nx] = true
                            q.add(Triple(ny,nx,cur.third+1))
                        }
                    }
                }
            }
        }
    }
    if(result == Int.MAX_VALUE) {
        println("NIE")
    }
    else {
        println("TAK")
        println(result)
    }
}

