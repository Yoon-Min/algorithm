import kotlin.math.*

val glacier = mutableSetOf<Pair<Int, Int>>()
val map = MutableList(300) { MutableList(300) { 0 } }
val nextDirection = listOf(listOf(-1, 0), listOf(0, 1), listOf(1, 0), listOf(0, -1))

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    for (i in 0..<n) {
        val line = readln().split(" ").map { it.toInt() }
        for (j in 0..<m) {
            map[i][j] = line[j]
            if (map[i][j] > 0) glacier.add(Pair(i, j))
        }
    }

    var year = 0
    while (true) {
        updateGlacier(n,m)
        year += 1
        if(glacier.isEmpty()) {
            year = 0
            break
        }
        if(isMultipleGroup(n,m)) {
            break
        }
    }
    println(year)
}

fun updateGlacier(n: Int, m: Int) {
    val tmpSeaAreaCounter = MutableList(n) { MutableList(m) { 0 } }
    val removalList = mutableListOf<Pair<Int,Int>>()
    glacier.forEach { p ->
        nextDirection.forEach { d ->
            val nextX = p.first + d.first()
            val nextY = p.second + d.last()
            if(nextX in 0..<n && nextY in 0..<m) {
                if(map[nextX][nextY] == 0) {
                    tmpSeaAreaCounter[p.first][p.second] += 1
                }
            }
        }
    }
    glacier.forEach { p ->
        val curGlacierHeight = map[p.first][p.second]
        val nextGlacierHeight = curGlacierHeight - tmpSeaAreaCounter[p.first][p.second]
        if(nextGlacierHeight < 0) {
            map[p.first][p.second] = 0
        }
        else {
            map[p.first][p.second] = nextGlacierHeight
        }
        if(nextGlacierHeight <= 0) {
            removalList.add(p)
        }
    }
    removalList.forEach { p ->
        glacier.remove(p)
    }
}

fun isMultipleGroup(n: Int, m: Int): Boolean {
    val isVisited = MutableList(n) { MutableList(m) { false } }
    var groupCounter = 0
    glacier.forEach { p ->
        if(!isVisited[p.first][p.second]) {
            isVisited[p.first][p.second] = true
            bfs(p, isVisited)
            groupCounter += 1
        }
        if (groupCounter > 1) return true
    }
    return false
}

fun bfs(start: Pair<Int, Int>, isVisited: MutableList<MutableList<Boolean>>) {
    val q = ArrayDeque<Pair<Int, Int>>().also { it.add(start) }
    while (q.isNotEmpty()) {
        val cur = q.removeFirst()
        nextDirection.forEach { d ->
            val nextX = cur.first + d.first()
            val nextY = cur.second + d.last()
            if (
                nextX in isVisited.indices &&
                nextY in isVisited.first().indices &&
                map[nextX][nextY] > 0 &&
                !isVisited[nextX][nextY]
            ) {
                isVisited[nextX][nextY] = true
                q.addLast(Pair(nextX,nextY))
            }
        }
    }
}