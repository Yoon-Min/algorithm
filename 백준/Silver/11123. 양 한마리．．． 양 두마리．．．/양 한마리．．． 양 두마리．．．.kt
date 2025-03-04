import kotlin.math.*

val dVector = listOf(Pair(0,1), Pair(-1,0), Pair(0,-1), Pair(1,0))

fun main() = with(System.`in`.bufferedReader()) {
    repeat(readLine().toInt()) {
        val (h,w) = readLine().split(" ").map { it.toInt()}
        val map = List(h) { readLine() }
        val isVisited = List(h) { MutableList(w) { false } }
        var group = 0
        for(i in 0 until h) {
            for(j in 0 until w) {
                if(!isVisited[i][j] && map[i][j] == '#') {
                    isVisited[i][j] = true
                    val q = ArrayDeque<Pair<Int,Int>>().also { it.add(Pair(i,j)) }
                    while(q.isNotEmpty()) {
                        val cur = q.removeFirst()
                        dVector.forEach { v ->
                            val nx = v.second + cur.second
                            val ny = v.first + cur.first
                            if(nx in 0 until w && ny in 0 until h && !isVisited[ny][nx] && map[ny][nx] == '#') {
                                isVisited[ny][nx] = true
                                q.add(Pair(ny,nx))
                            }
                        }
                    }
                    group += 1
                }
            }
        }
        println(group)
    }
}

