import kotlin.math.*

val dVector = listOf(Pair(0,1), Pair(1,0), Pair(0,-1), Pair(-1,0))
val emptyPos = mutableListOf<Pair<Int,Int>>()
val comb = mutableListOf<List<Int>>()

fun main() = with(System.`in`.bufferedReader()) {
    val (n,m) = readLine().split(" ").map { it.toInt() }
    val map = List(n) { readLine().split(" ").map { it.toInt() }.toMutableList() }
    var result = 0

    for(i in 0 until n) {
        for(j in 0 until m) {
            if(map[i][j] == 0) emptyPos.add(Pair(i,j))
        }
    }
    setCombination()

    for(tmpEmptyPosIndex in comb) {
        tmpEmptyPosIndex.forEach { idx ->
            val pos = emptyPos[idx]
            map[pos.first][pos.second] = 1
        }

        var count = 0
        val visited = List(n) { MutableList(m) { false } }

        for(i in 0 until n) {
            for(j in 0 until m) {
                if(map[i][j] == 2 && !visited[i][j]) {
                    visited[i][j] = true

                    var isValid = true
                    var tmpCount = 1
                    val q = ArrayDeque<Pair<Int,Int>>().also { it.add(Pair(i,j)) }

                    while(q.isNotEmpty()) {
                        val cur = q.removeFirst()

                        for(v in dVector) {
                            val nx = v.second + cur.second
                            val ny = v.first + cur.first
                            if(nx !in 0 until m || ny !in 0 until n) continue

                            if(map[ny][nx] == 0) {
                                isValid = false
                                continue
                            }
                            if(map[ny][nx] == 2 && !visited[ny][nx]) {
                                tmpCount += 1
                                visited[ny][nx] = true
                                q.add(Pair(ny,nx))
                            }
                        }
                    }
                    if(isValid) count += tmpCount
                }
            }
        }
        result = max(result, count)


        tmpEmptyPosIndex.forEach { idx ->
            val pos = emptyPos[idx]
            map[pos.first][pos.second] = 0
        }
    }
    println(result)
}

fun setCombination(s: Int = 0, tmp: MutableList<Int> = mutableListOf()) {
    if(tmp.size == 2) {
        comb.add(tmp.toList())
        return
    }
    for(i in s..emptyPos.lastIndex) {
        tmp.add(i)
        setCombination(i+1, tmp)
        tmp.removeLast()
    }
}