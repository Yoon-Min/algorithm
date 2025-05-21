import kotlin.math.*

val dVector = listOf(Pair(0,1), Pair(1,0), Pair(-1,0), Pair(0,-1))

fun main() = with(System.`in`.bufferedReader()) {
    val (m,n) = readLine().split(" ").map { it.toInt() }
    val map = List(n) { readLine().map { it.code - 48 } }
    val brokenWall = List(n) { MutableList(m) {Int.MAX_VALUE} }
    var minBrokenWall = Int.MAX_VALUE

    val q = ArrayDeque<Triple<Int,Int,Int>>().also { it.add(Triple(0,0,0)) }
    brokenWall[0][0] = 0

    while(q.isNotEmpty()) {
        val cur = q.removeFirst()
        var broken = cur.third

        if(cur.first == n-1 && cur.second == m-1) {
            minBrokenWall = min(minBrokenWall, broken)
            if(broken == 0) break
        }

        dVector.forEach { v ->
            val nx = v.second + cur.second
            val ny = v.first + cur.first

            if(nx in 0 until m && ny in 0 until n) {
                val nextBroken = if(map[ny][nx] == 1) broken+1 else broken
                if(nextBroken < brokenWall[ny][nx]) {
                    brokenWall[ny][nx] = nextBroken
                    q.add(Triple(ny,nx,nextBroken))
                }
            }
        }
    }

    println(minBrokenWall)
}