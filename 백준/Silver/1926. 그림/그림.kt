import kotlin.math.*

val dVector = listOf(Pair(0,1), Pair(1,0), Pair(0,-1), Pair(-1,0))

fun main() = with(System.`in`.bufferedReader()) {
    val (n,m) = readLine().split(" ").map { it.toInt() }
    val map = List(n) { readLine().split(" ").map { it.toInt() }.toMutableList() }
    var resultMaxArea = 0
    var resultGroup = 0
    for(i in 0 until n) {
        for(j in 0 until m) {
            if(map[i][j] == 1) {
                var tmpArea = 1
                val q = ArrayDeque<Pair<Int,Int>>().also { it.add(Pair(i,j)) }
                map[i][j] = 0
                while(q.isNotEmpty()) {
                    val cur = q.removeFirst()
                    dVector.forEach { v ->
                        val nx = v.second + cur.second
                        val ny = v.first + cur.first
                        if(nx in 0 until m && ny in 0 until n && map[ny][nx] == 1) {
                            map[ny][nx] = 0
                            q.add(Pair(ny,nx))
                            tmpArea += 1
                        }
                    }
                }
                resultMaxArea = max(resultMaxArea, tmpArea)
                resultGroup += 1
            }
        }
    }
    println(resultGroup)
    println(resultMaxArea)
}

