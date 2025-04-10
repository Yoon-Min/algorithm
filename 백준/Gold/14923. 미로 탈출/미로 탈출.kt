import kotlin.math.*
import kotlin.system.exitProcess

val dVector = listOf(Pair(0,1), Pair(1,0), Pair(0,-1), Pair(-1,0))

fun main() = with(System.`in`.bufferedReader()) {
    val (n,m) = readLine().split(" ").map { it.toInt() }
    val (hx,hy) = readLine().split(" ").map { it.toInt()-1 }
    val (ex,ey) = readLine().split(" ").map { it.toInt()-1 }
    val map = List(n) { readLine().split(" ").map { it.toInt() } }
    val dist = List(n) { List(m) { MutableList(2) {Int.MAX_VALUE} } }.also {
        if (map[hx][hy] == 1) { it[hx][hy][1] = 0 }
        else { it[hx][hy][0] = 0 }
    }
    val q = ArrayDeque<Node>().also { it.add(Node(hy,hx, map[hx][hy] == 1)) }

    while(q.isNotEmpty()) {
        val cur = q.removeFirst()
        val nextDist = cur.dist+1

        if(cur.x == ey && cur.y == ex) {
            println(cur.dist)
            exitProcess(0)
        }

        for(v in dVector) {
            val nx = v.second + cur.x
            val ny = v.first + cur.y
            var usedMagicStaff = if(cur.usedMagicStaff) 1 else 0

            if(nx !in 0 until m || ny !in 0 until n) continue
            if(map[ny][nx] == 1 && cur.usedMagicStaff) continue
            if(map[ny][nx] == 1 && !cur.usedMagicStaff) {
                usedMagicStaff = 1
            }
            if(nextDist < dist[ny][nx][usedMagicStaff]) {
                dist[ny][nx][usedMagicStaff] = nextDist
                q.add(Node(nx,ny,usedMagicStaff == 1, nextDist))
            }
        }
    }
    println(-1)

}

data class Node(
    val x: Int,
    val y: Int,
    val usedMagicStaff: Boolean = false,
    val dist: Int = 0
)