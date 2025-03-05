import kotlin.math.*

val dVector = listOf(Pair(0,1), Pair(1,0), Pair(0,-1), Pair(-1,0))

fun main() = with(System.`in`.bufferedReader()) {
    val (m,n) = readLine().split(" ").map { it.toInt() }
    val map = List(n) { readLine().toMutableList() }
    var whiteGroup = 0
    var blueGroup = 0
    for(i in 0 until n) {
        for(j in 0 until m ) {
            if(map[i][j] != '.') {
                if(map[i][j] == 'W') {
                    whiteGroup += bfs(Pair(i,j), 'W', map).run { this * this }
                }
                else {
                    blueGroup += bfs(Pair(i,j), 'B', map).run { this * this }
                }
            }
        }
    }
    println("$whiteGroup $blueGroup")
}

fun bfs(s: Pair<Int,Int>, comparator: Char, map: List<MutableList<Char>>): Int {
    val q = ArrayDeque<Pair<Int,Int>>().also { it.add(s) }
    map[s.first][s.second] = '.'
    var groupMember = 1
    while(q.isNotEmpty()) {
        val cur = q.removeFirst()
        dVector.forEach { v ->
            val nx = v.second + cur.second
            val ny = v.first + cur.first
            if(nx in map.first().indices && ny in map.indices && map[ny][nx] != '.' && map[ny][nx] == comparator) {
                map[ny][nx] = '.'
                groupMember += 1
                q.add(Pair(ny,nx))
            }
        }
    }
    return groupMember
}

