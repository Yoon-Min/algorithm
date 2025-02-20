
val dVector = listOf(Pair(0,1), Pair(1,0), Pair(0,-1), Pair(-1,0))

fun main() = with(System.`in`.bufferedReader()) {
    val (n,m) = readLine().split(" ").map { it.toInt() }
    val map = List(n) { MutableList(m) {-1} }
    val isVisited = List(n) { MutableList(m) {false} }
    var groupCount = 0
    repeat(n) {
        val input = readLine().split(" ")
        for(i in 0 until m) {
            val starting = i * 3
            var sum = 0
            for(j in starting..starting+2) {
                sum += input[j].toInt()
            }
            map[it][i] = sum/3
        }
    }
    val t = readLine().toInt()
    for(i in 0 until n) {
        for (j in 0 until m) {
            if(map[i][j] >= t) {
                map[i][j] = 255
            }
            else {
                map[i][j] = 0
            }
        }
    }
    for(i in 0 until n) {
        for(j in 0 until m) {
            if(!isVisited[i][j] && map[i][j] == 255) {
                isVisited[i][j] = true
                val q = ArrayDeque<Pair<Int,Int>>().also { it.add(Pair(i,j) ) }
                while(q.isNotEmpty()) {
                    val cur = q.removeFirst()
                    for(v in dVector) {
                        val nx = v.second + cur.second
                        val ny = v.first + cur.first
                        if(nx in 0 until m  && ny in 0 until n && map[ny][nx] == 255 && !isVisited[ny][nx]) {
                            isVisited[ny][nx] = true
                            q.add(Pair(ny,nx))
                        }
                    }
                }
                groupCount += 1
            }
        }
    }
    println(groupCount)
}

