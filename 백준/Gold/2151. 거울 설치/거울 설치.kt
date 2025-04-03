val dVector = listOf(Pair(0, 1), Pair(1, 0), Pair(0, -1), Pair(-1, 0))
const val MAX_VALUE = Int.MAX_VALUE

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val door = mutableListOf<Pair<Int, Int>>()
    val q = ArrayDeque<Triple<Pair<Int, Int>, Int, Int>>()
    val map = List(n) { readLine().toList() }.also {
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (it[i][j] == '#') door.add(Pair(i, j))
            }
        }
    }
    val usedMirror = List(n) { List(n) { MutableList(4) {MAX_VALUE} } }
    for(nextDirection in dVector.indices) {
        usedMirror[door.first().first][door.first().second][nextDirection] = 0
        q.add(Triple(door.first(), nextDirection, 0))
    }

    while (q.isNotEmpty()) {
        val cur = q.removeFirst()
        val curPos = cur.first
        val curDirection = cur.second
        val curUsedMirror = cur.third

        for (nextDirection in dVector.indices) {
            val v = dVector[nextDirection]
            val nx = v.second + curPos.second
            val ny = v.first + curPos.first
            val nextUsedMirror = curUsedMirror + if(map[curPos.first][curPos.second] == '!' && curDirection != nextDirection) 1 else 0

            if (nx !in 0 until n || ny !in 0 until n) continue
            if (map[ny][nx] == '*') continue
            if (map[curPos.first][curPos.second] == '.' && curDirection != nextDirection) continue
            if (usedMirror[ny][nx][nextDirection] <= nextUsedMirror) continue

            usedMirror[ny][nx][nextDirection] = nextUsedMirror
            q.add(Triple(Pair(ny,nx),nextDirection,nextUsedMirror))
        }
    }
    println(usedMirror[door.last().first][door.last().second].min())
}

