
val dVector = listOf(Pair(0,1), Pair(1,0), Pair(0,-1), Pair(-1,0))

fun main() = with(System.`in`.bufferedReader()) {
    val (r,c,n) = readLine().split(" ").map { it.toInt() }
    val map = List(r) { readLine().map { if(it == 'O') Pair(it, 1) else Pair(it,0) }.toMutableList() }
    var time = 1
    while(time < n) {
        for(i in 0 until r) {
            for(j in 0 until c) {
                if(map[i][j].first == 'O') {
                    map[i][j] = map[i][j].copy(second = map[i][j].second+1)
                }
                else {
                    map[i][j] = map[i][j].copy(first = 'O')
                }
            }
        }
        time += 1
        if(time == n) break
        val s = mutableSetOf<Pair<Int,Int>>()
        for(i in 0 until r) {
            for(j in 0 until c) {
                if(map[i][j].first == 'O' && map[i][j].second == 2) {
                    s.add(Pair(i,j))
                }
                else if(map[i][j].first == 'O' && map[i][j].second == 0) {
                    map[i][j] = map[i][j].copy(second = 1)
                }
            }
        }
        s.forEach { p ->
            map[p.first][p.second] = map[p.first][p.second].copy('.', 0)
            dVector.forEach { v ->
                val nx = v.second + p.second
                val ny = v.first + p.first
                if(nx in 0 until c && ny in 0 until r && map[ny][nx].first == 'O') {
                    map[ny][nx] = map[ny][nx].copy('.', 0)
                }
            }
        }
        time += 1
    }
    for(inner in map) { println(inner.map { it.first }.joinToString("")) }
}