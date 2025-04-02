val dVector = listOf(Pair(0, 1), Pair(1, 0), Pair(0, -1), Pair(-1, 0))

fun main() = with(System.`in`.bufferedReader()) {
    val (w, h) = readLine().split(" ").map { it.toInt() }
    val cPos = mutableListOf<Pair<Int, Int>>()
    val q = ArrayDeque<Triple<Pair<Int,Int>,Int,Int>>()
    val map = List(h) { readLine().toList() }.also {
        for (i in 0 until h) {
            for (j in 0 until w) {
                if (it[i][j] == 'C') cPos.add(Pair(i, j))
            }
        }
    }
    val usedMirror = List(h) { i ->
        MutableList(w) { j ->
            when (map[i][j]) {
                '.', 'C' -> Pair(Int.MAX_VALUE,-1)
                else -> Pair(-1,-1)
            }
        }
    }
    q.add(Triple(cPos.first(),  -1, 0))
    usedMirror[cPos.first().first][cPos.first().second] = usedMirror[cPos.first().first][cPos.first().second].copy(first = 0)

    while (q.isNotEmpty()) {
        val cur = q.removeFirst()
        val curPos = cur.first
        val curDirection = cur.second
        val curUsedMirror = cur.third

        for (i in dVector.indices) {
            val v = dVector[i]
            val nx = v.second + curPos.second
            val ny = v.first + curPos.first

            if (nx !in 0 until w || ny !in 0 until h) continue
            if (map[ny][nx] == '*') continue
            val nextTotalMirror = curUsedMirror + if (curDirection == i || curDirection == -1) 0 else 1

            if (nextTotalMirror < usedMirror[ny][nx].first || (nextTotalMirror == usedMirror[ny][nx].first && usedMirror[ny][nx].second != i)) {
                usedMirror[ny][nx] = usedMirror[ny][nx].copy(first = nextTotalMirror, second = i)
                q.add(Triple(Pair(ny, nx), i, nextTotalMirror))
            }
        }
    }
    println(usedMirror[cPos.last().first][cPos.last().second].first)
}