import kotlin.system.exitProcess

val dVector = listOf(Pair(0,1), Pair(1,0), Pair(0,-1), Pair(-1,0))
var s = Pair(0,0)
var d = Pair(0,0)

fun main() = with(System.`in`.bufferedReader()) {
    val (n,m) = readLine().split(" ").map { it.toInt() }
    val map = List(n) { readLine().toList() }
    val customMap = List(n) { MutableList(m) {0} }

    val q = ArrayDeque<Triple<Int,Int,Int>>()
    for(i in 0 until n ) {
        for(j in 0 until m ) {
            if(map[i][j] == 'S') {
                s = s.copy(i,j)
                customMap[i][j] = 0
            }
            else if(map[i][j] == 'D') {
                d = d.copy(i,j)
                customMap[i][j] = -1
            }
            else if(map[i][j] == '*') {
                customMap[i][j] = 1
                q.add(Triple(i,j,0))
            }
            else if(map[i][j] == 'X') {
                customMap[i][j] = -1
            }
        }
    }
    while(q.isNotEmpty()) {
        val cur = q.removeFirst()
        dVector.forEach { v ->
            val nx = v.second + cur.second
            val ny = v.first + cur.first
            if(nx in 0 until m && ny in 0 until n && customMap[ny][nx] == 0) {
                customMap[ny][nx] = cur.third+1
                q.add(Triple(ny,nx,cur.third+1))
            }
        }
    }
    customMap[d.first][d.second] = Int.MAX_VALUE
    for(i in 0 until n ) {
        for (j in 0 until m) {
            if (customMap[i][j] == 0) {
                customMap[i][j] = Int.MAX_VALUE
            }
        }
    }

    q.add(Triple(s.first,s.second,0))
    val visited = List(n) { MutableList(m) { false } }.also { it[s.first][s.second] = true }
    while(q.isNotEmpty()) {
        val cur = q.removeFirst()
        if(cur.first == d.first && cur.second == d.second) {
            println(cur.third)
            exitProcess(0)
        }
        dVector.forEach { v ->
            val nx = v.second + cur.second
            val ny = v.first + cur.first
            if(nx in 0 until m && ny in 0 until n && !visited[ny][nx] && customMap[ny][nx] != -1 && cur.third+1 < customMap[ny][nx]) {
                visited[ny][nx] = true
                q.add(Triple(ny,nx,cur.third+1))
            }
        }
    }
    println("KAKTUS")
}