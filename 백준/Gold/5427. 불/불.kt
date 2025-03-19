import kotlin.math.*

val dVector = listOf(Pair(0, 1), Pair(1, 0), Pair(0, -1), Pair(-1, 0))

fun main() = with(System.`in`.bufferedReader()) {
    repeat(readLine().toInt()) {
        var result = 0
        val q = ArrayDeque<Triple<Int, Int, Int>>()
        val fireQ = ArrayDeque<Triple<Int, Int, Int>>()
        val (w, h) = readLine().split(" ").map { it.toInt() }
        val map = List(h) { i ->
            readLine().mapIndexed { j, c ->
                when (c) {
                    '#' -> -1
                    '.' -> 0
                    '*' -> {
                        fireQ.add(Triple(i, j, 0))
                        1
                    }

                    else -> {
                        q.add(Triple(i, j, 0))
                        2
                    }
                }
            }.toMutableList()
        }
        val fireMap = List(h) { i -> MutableList(w) { j -> map[i][j] } }
        fireMap[q.first().first][q.first().second] = 0

        while (fireQ.isNotEmpty()) {
            val cur = fireQ.removeFirst()
            dVector.forEach { v ->
                val nx = v.second + cur.second
                val ny = v.first + cur.first
                if (nx in 0 until w && ny in 0 until h && fireMap[ny][nx] == 0) {
                    fireMap[ny][nx] = cur.third + 1
                    fireQ.add(Triple(ny, nx, cur.third + 1))
                }
            }
        }

//        fireMap.forEach { println(it.joinToString(" ")) }

        while (q.isNotEmpty()) {
            val cur = q.removeFirst()

            if (cur.first !in 1 until h - 1 || cur.second !in 1 until w - 1) {
                result = cur.third + 1
                break
            }

            dVector.forEach { v ->
                val nx = v.second + cur.second
                val ny = v.first + cur.first
                if (nx in 0 until w &&
                    ny in 0 until h && map[ny][nx] == 0 &&
                    (fireMap[ny][nx] == 0 || cur.third + 1 < fireMap[ny][nx])
                ) {
                    map[ny][nx] = cur.third + 1
                    q.add(Triple(ny, nx, cur.third + 1))
                }
            }
        }
//        println()
//        map.forEach { println(it.joinToString(" ")) }

        println(if (result == 0) "IMPOSSIBLE" else result)
    }
}
