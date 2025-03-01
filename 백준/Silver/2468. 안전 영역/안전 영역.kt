import kotlin.math.*

val dVector = listOf(Pair(0,1), Pair(-1,0), Pair(0,-1), Pair(1,0))

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val map = List(n) { readLine().split(" ").map { it.toInt() } }
    var minHeight = Int.MAX_VALUE
    var maxHeight = 0
    var maxGroup = 0

    for(i in map.indices) {
        for(j in map.indices) {
            minHeight = min(minHeight, map[i][j])
            maxHeight = max(maxHeight, map[i][j])
        }
    }

    for(nextHeight in 0..maxHeight) {
        val isSubmerged = List(n) { MutableList(n) { false } }
        for(i in isSubmerged.indices) {
            for(j in isSubmerged.indices) {
                if(map[i][j] <= nextHeight) {
                    isSubmerged[i][j] = true
                }
            }
        }
        maxGroup = max(maxGroup, bfs(isSubmerged))
    }
    println(maxGroup)
}

fun bfs(map: List<MutableList<Boolean>>): Int {
    var groupCount = 0
    for(i in map.indices) {
        for(j in map.indices) {
            if(!map[i][j]) {
                map[i][j] = true
                val q = ArrayDeque<Pair<Int,Int>>().also { it.add(Pair(i,j)) }
                while(q.isNotEmpty()) {
                    val cur = q.removeFirst()
                    dVector.forEach { v ->
                        val nx = v.second + cur.second
                        val ny = v.first + cur.first
                        if(nx in map.indices && ny in map.indices && !map[ny][nx]) {
                            map[ny][nx] = true
                            q.add(Pair(ny,nx))
                        }
                    }
                }
                groupCount += 1
            }
        }
    }
    return groupCount
}


