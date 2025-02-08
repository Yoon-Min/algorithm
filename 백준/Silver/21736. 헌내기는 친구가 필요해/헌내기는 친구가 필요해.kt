fun main() {
    val dVector = listOf(Pair(0,1), Pair(1,0), Pair(0,-1), Pair(-1,0))
    val (n,m) = readln().split(" ").map { it.toInt() }
    val map = List(n) { readln() }
    var count = 0
    var s = Pair(0,0)
    for(i in 0 until n) {
        for(j in 0 until m) {
            if(map[i][j] == 'I') {
                s =  Pair(i,j)
            }
        }
    }
    val q = ArrayDeque<Pair<Int,Int>>().also { it.add(s) }
    val isVisited = MutableList(n) { MutableList(m) { false } }
    isVisited[s.first][s.second] = true

    while(q.isNotEmpty()) {
        val cur = q.removeFirst()
        if(map[cur.first][cur.second] == 'P') count += 1

        dVector.forEach { v ->
            val nx = v.first + cur.first
            val ny = v.second + cur.second
            if(nx in 0 until n && ny in 0 until m && map[nx][ny] != 'X' && !isVisited[nx][ny]) {
                isVisited[nx][ny] = true
                q.add(Pair(nx,ny))
            }
        }
    }
    if(count == 0) println("TT")
    else println(count)
}



