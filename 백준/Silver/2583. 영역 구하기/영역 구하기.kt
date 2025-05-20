import kotlin.math.*

val dVector = listOf(Pair(0,1), Pair(1,0), Pair(0,-1), Pair(-1,0))

fun main() = with(System.`in`.bufferedReader()) {
    val (m,n,k) = readLine().split(" ").map { it.toInt() }
    val map = List(n) { MutableList(m) {0} }
    val areaList = mutableListOf<Int>()

    repeat(k) {
        val (si,sj,ei,ej) = readLine().split(" ").map { it.toInt() }
        for(i in si until ei) {
            for(j in sj until ej) {
                map[i][j] = 1
            }
        }
    }

    for(i in 0 until n) {
        for(j in 0 until m) {
            if(map[i][j] == 0) {
                map[i][j] = 1
                var area = 1
                val q = ArrayDeque<Pair<Int,Int>>().also { it.add(Pair(i,j)) }
                while(q.isNotEmpty()) {
                    val cur = q.removeFirst()
                    dVector.forEach { v ->
                        val nx = v.second + cur.second
                        val ny = v.first + cur.first
                        if(nx in 0 until m && ny in 0 until n && map[ny][nx] == 0) {
                            q.add(Pair(ny,nx))
                            map[ny][nx] = 1
                            area += 1
                        }
                    }
                }
                areaList.add(area)
            }
        }
    }
    println(areaList.size)
    println(areaList.sorted().joinToString(" "))
}