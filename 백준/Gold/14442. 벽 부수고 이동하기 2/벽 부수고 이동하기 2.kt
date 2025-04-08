import kotlin.system.exitProcess

val dVector = listOf(Pair(0, 1), Pair(1, 0), Pair(0, -1), Pair(-1, 0))

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m, k) = readLine().split(" ").map { it.toInt() }
    val map = List(n) { readLine().map { it.code - 48 } }
    val q = ArrayDeque<Node>().also { it.add(Node(0,0,1,0))}
    val minDist = List(k+1) { List(n) { MutableList(m) { Int.MAX_VALUE } } }.also {
        for(i in 0..k) {
            it[i][0][0] = 1
        }
    }
    while(q.isNotEmpty()) {
        val cur = q.removeFirst()

        if(cur.y == n-1 && cur.x == m-1) {
            println(cur.dist)
            exitProcess(0)
        }

        for(v in dVector) {
            val nx = v.second + cur.x
            val ny = v.first + cur.y
            val nextDist = cur.dist+1
            var nextDestroyedWall = cur.destroyedWall

            if(nx !in 0 until m || ny !in 0 until n) continue
            if(map[ny][nx] == 1 && cur.destroyedWall >= k) continue
            if(map[ny][nx] == 1) nextDestroyedWall += 1

            if(minDist[nextDestroyedWall][ny][nx] == Int.MAX_VALUE) {
                minDist[nextDestroyedWall][ny][nx] = nextDist
                q.add(Node(nx,ny, nextDist, nextDestroyedWall))
            }
        }
    }
    println(-1)
}

data class Node(
    val x: Int,
    val y: Int,
    val dist: Int,
    val destroyedWall: Int
)

/*
1. 부순 벽의 개수가 더 많다면 거리가 더 작아야 한다.
2. 부순 벽의 개수가 같다면 거리가 더 작아야 한다.
3. 부순 벽의 개수가 더 적다면 현재 누적된 거리가 더 커도, 최소 경로에 도달할 가능성이 있다.
 */