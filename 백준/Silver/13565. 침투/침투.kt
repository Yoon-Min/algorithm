
val dVector = listOf(Pair(0,1), Pair(1,0), Pair(0,-1), Pair(-1,0))

fun main() = with(System.`in`.bufferedReader()) {
    val (m,n) = readLine().split(" ").map { it.toInt() }
    val map = List(m) { readLine() }
    val isVisited = List(m) { MutableList(n) {false} }
    for(i in 0 until n) {
        if(!isVisited[0][i]) {
            isVisited[0][i] = true
            val q = ArrayDeque<Pair<Int,Int>>().also { it.add(Pair(0,i) ) }
            while(q.isNotEmpty()) {
                val cur = q.removeFirst()
                for(v in dVector) {
                    val nx = v.second + cur.second
                    val ny = v.first + cur.first
                    if(nx in 0 until n && ny in 0 until m && map[ny][nx] == '0' && !isVisited[ny][nx]) {
                        isVisited[ny][nx] = true
                        q.add(Pair(ny,nx))
                    }
                }
            }
        }
    }
    for(i in 0 until n) {
        if(isVisited[m-1][i]) {
            println("YES")
            return
        }
    }
    println("NO")
}

