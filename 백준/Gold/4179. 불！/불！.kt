import java.util.*

val dVector = listOf(Pair(0,1), Pair(1,0), Pair(0,-1), Pair(-1,0))

fun main() = with(System.`in`.bufferedReader()) {
    val q = ArrayDeque<Triple<Int,Int,Int>>()
    var startingPoint = Pair(0,0)

    val (r,c) = readLine().split(" ").map { it.toInt() }
    val map = List(r) { readLine().toList() }
    val fireMap = List(r) { MutableList(c) { -1 } }

    for(i in 0 until r) {
        for(j in 0 until c) {
            when(map[i][j]) {
                'J' -> startingPoint = startingPoint.copy(i,j)
                'F' -> {
                    q.add(Triple(i,j,0))
                    fireMap[i][j] = 0
                }
            }
        }
    }

    while(q.isNotEmpty()) {
        val cur = q.removeFirst()

        for(v in dVector) {
            val nx = v.second + cur.second
            val ny = v.first + cur.first
            val nextTime = cur.third + 1

            if(nx !in 0 until c || ny !in 0 until r) continue
            if(map[ny][nx] == '#' || fireMap[ny][nx] > -1) continue

            q.add(Triple(ny,nx,nextTime))
            fireMap[ny][nx] = nextTime
        }
    }

    for(i in 0 until r) {
        for(j in 0 until c) {
            if(map[i][j] != '#' && fireMap[i][j] == -1) {
                fireMap[i][j] = Int.MAX_VALUE
            }
        }
    }

    val visited = List(r) { MutableList(c) { false } }

    q.add(Triple(startingPoint.first,startingPoint.second,0))
    visited[q.first.first][q.first.second] = true

    while(q.isNotEmpty()) {
        val cur = q.removeFirst()

        if(cur.first !in 0 until r || cur.second !in 0 until c) {
            println(cur.third)
            return
        }

        for(v in dVector) {
            val nx = v.second + cur.second
            val ny = v.first + cur.first
            val nextTime = cur.third + 1

            if(nx !in 0 until c || ny !in 0 until r) {
                q.add(Triple(ny,nx,nextTime))
                continue
            }

            if(map[ny][nx] == '#' || nextTime >= fireMap[ny][nx] || visited[ny][nx]) continue

            q.add(Triple(ny,nx,nextTime))
            visited[ny][nx] = true
        }
    }

    println("IMPOSSIBLE")
}