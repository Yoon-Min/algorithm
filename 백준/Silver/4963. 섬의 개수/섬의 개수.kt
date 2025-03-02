import kotlin.math.*

val dVector = listOf(Pair(0,1), Pair(-1,0), Pair(0,-1), Pair(1,0), Pair(1,1), Pair(1,-1), Pair(-1,1), Pair(-1,-1))

fun main() = with(System.`in`.bufferedReader()) {
    while(true) {
        val (w,h) = readLine().split(" ").map { it.toInt() }
        if(w == 0 && h == 0) {
            break
        }
        var island = 0
        val map = List(h) { readLine().split(" ").map { it.toInt() }.toMutableList() }
        for(i in 0 until h) {
            for(j in 0 until w) {
                if(map[i][j] == 1) {
                    map[i][j] = 0
                    val q = ArrayDeque<Pair<Int,Int>>().also { it.add(Pair(i,j)) }
                    while(q.isNotEmpty()) {
                        val cur = q.removeFirst()
                        dVector.forEach { v ->
                            val nx = cur.second + v.second
                            val ny = cur.first + v.first
                            if(nx in 0 until w && ny in 0 until h && map[ny][nx] == 1) {
                                map[ny][nx] = 0
                                q.add(Pair(ny,nx))
                            }
                        }
                    }
                    island += 1
                }
            }
        }
        println(island)
    }
}

