import kotlin.system.exitProcess

val dVector = listOf(Pair(0,1), Pair(1,0), Pair(0,-1), Pair(-1,0))

fun main() = with(System.`in`.bufferedReader()) {
    val (n,m,a,b,k) = readLine().split(" ").map { it.toInt() }
    val map = List(n) { MutableList(m) {0} }
    val q = ArrayDeque<Triple<Int,Int,Int>>()
    val isVisited = List(n) { MutableList(m) {false} }
    val width = 0 until m
    val height = 0 until n
    repeat(k) {
        val (i,j) = readLine().split(" ").map { it.toInt() - 1 }
        map[i][j] = 1
    }
    val s = readLine().split(" ").map { it.toInt() - 1 }
    val e = readLine().split(" ").map { it.toInt() - 1 }
    q.add(Triple(s[0],s[1],0))
    isVisited[s[0]][s[1]] = true

    while(q.isNotEmpty()) {
        val cur = q.removeFirst()
        if(cur.first == e[0] && cur.second == e[1]) {
            println(cur.third)
            return
        }
        for(v in dVector) {
            val nx = v.second + cur.second
            val ny = v.first + cur.first
            val endX = nx + (b-1)
            val endY = ny + (a-1)
            if(!width.contains(nx) || !width.contains(endX) || !height.contains(ny) || !height.contains(endY)) continue
            if(isVisited[ny][nx]) continue
            if(checkWall(ny..endY, nx..endX, map)) continue
            isVisited[ny][nx] = true
            q.add(Triple(ny,nx,cur.third+1))
        }
    }
    println(-1)
}

fun checkWall(colRange: IntRange, rowRange: IntRange, map: List<MutableList<Int>>): Boolean {
    for(i in colRange) {
        for(j in rowRange) {
            if(map[i][j] == 1) return true
        }
    }
    return false
}