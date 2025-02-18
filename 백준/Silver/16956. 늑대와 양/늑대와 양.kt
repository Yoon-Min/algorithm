
val dVector = listOf(Pair(0,1), Pair(1,0), Pair(0,-1), Pair(-1,0))

fun main() = with(System.`in`.bufferedReader()) {
    val (r,c) = readLine().split(" ").map { it.toInt() }
    val map = List(r) { readLine().toMutableList() }

    for(i in 0 until r) {
        for(j in 0 until c) {
            if(map[i][j] == 'W') {
                dVector.forEach { v ->
                    val nx = v.second + j
                    val ny = v.first + i
                    if(nx in 0 until c && ny in 0 until r) {
                        if(map[ny][nx] == 'S') {
                            println(0)
                            return
                        }
                        else if(map[ny][nx] == '.') {
                            map[ny][nx] = 'D'
                        }
                    }
                }
            }
        }
    }
    println(1)
    for(inner in map) { println(inner.joinToString("")) }
}