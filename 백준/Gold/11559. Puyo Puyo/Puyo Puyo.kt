import kotlin.system.exitProcess

val dVector = listOf(Pair(0,1), Pair(1,0), Pair(0,-1), Pair(-1,0))
val map = List(12) { readln().toMutableList() }

fun main() = with(System.`in`.bufferedReader()) {
    var count = 0
    while(true) {
        val visited = List(12) { MutableList(6) { false } }
        var explosionCount = 0
        for(i in 0 until 12) {
            for(j in 0 until 6) {
                if(map[i][j] != '.' && !visited[i][j]) {
                    visited[i][j] = true
                    var groupMemberCount = 1
                    val q = ArrayDeque<Pair<Int,Int>>().also { it.add(Pair(i,j)) }
                    val groupMember = mutableListOf<Pair<Int,Int>>().also { it.add(Pair(i,j))}

                    while(q.isNotEmpty()) {
                        val cur = q.removeFirst()
                        val curColor = map[cur.first][cur.second]
                        dVector.forEach { v ->
                            val nx = v.second + cur.second
                            val ny = v.first + cur.first
                            if(nx in 0 until 6 && ny in 0 until 12 && !visited[ny][nx] && map[ny][nx] == curColor) {
                                visited[ny][nx] = true
                                groupMemberCount += 1
                                q.add(Pair(ny,nx))
                                groupMember.add(Pair(ny,nx))
                            }
                        }
                    }
                    if(groupMemberCount >= 4) {
                        repeat(groupMember.size) {
                            groupMember.removeLast().also { map[it.first][it.second] = '.' }
                        }
                        explosionCount += 1
                    }
                }
            }
        }

        if(explosionCount == 0) break
        for(i in 11 downTo 0) {
            for(j in 0 until 6) {
                if(map[i][j] != '.') {
                    var col = i+1
                    while(col < 12 && map[col][j] == '.') {
                        col += 1
                    }
                    map[col-1][j] = map[i][j]
                    if(col-1 != i ) map[i][j] = '.'
                }
            }
        }
//        map.forEach { println(it.joinToString(""))}.also { println() }
        count += 1
    }
    println(count)
}

fun bfs(s: Pair<Int,Int>) {

}