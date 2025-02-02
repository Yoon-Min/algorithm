import kotlin.math.*
import kotlin.system.exitProcess

val dVector = listOf(Pair(0,1), Pair(1,0), Pair(0,-1), Pair(-1,0))

fun main() {
    val (n,m) = readln().split(" ").map { it.toInt() }
    val map = MutableList(n) { MutableList(m) {0} }
    val q = ArrayDeque<Pair<Int,Int>>()
    val dist = MutableList(n) { MutableList(m) { -1 } }
    var startingPos = Pair(0,0)

    repeat(n) { i ->
        val inputLine = readln().split(" ").map { it.toInt() }
        for(j in inputLine.indices) {
            map[i][j] = inputLine[j]
            if(inputLine[j] == 2) {
                startingPos = startingPos.copy(i,j)
            }
        }
    }

    q.addLast(startingPos)
    dist[startingPos.first][startingPos.second] = 0

    while(q.isNotEmpty()) {
        val cur = q.removeFirst()
        for(v in dVector) {
            val nx = cur.first + v.first
            val ny = cur.second + v.second
            if(nx !in 0..<n || ny !in 0..<m) continue
            if(map[nx][ny] == 0) {
                dist[nx][ny] = 0
            }
            else if(dist[nx][ny] == -1) {
                dist[nx][ny] = 1 + dist[cur.first][cur.second]
                q.add(Pair(nx,ny))
            }
        }
    }

    for(i in dist.indices) {
        for(j in dist[0].indices) {
            if(dist[i][j] == -1 && map[i][j] == 0)
                dist[i][j] = 0
        }
    }

    for(i in dist.indices) {
        println(dist[i].joinToString(" "))
    }
}