import kotlin.math.*

val dVector =
    listOf(Pair(0, 1), Pair(1, 0), Pair(0, -1), Pair(-1, 0), Pair(1, 1), Pair(1, -1), Pair(-1, -1), Pair(-1, 1))

fun main() = with(System.`in`.bufferedReader()) {
    val (h, w) = readLine().split(" ").map { it.toInt() }
    val map = List(h) { readLine().map { if (it == '.') 0 else it.code - 48 }.toMutableList() }

    val q = ArrayDeque<Triple<Int, Int,Int>>()
    var wave = 0

    for (i in 0 until h) {
        for (j in 0 until w) {
            if (map[i][j] == 0) {
                q.add(Triple(i, j,0))
            }
        }
    }

    while(q.isNotEmpty()) {
        val cur = q.removeFirst()
        wave = cur.third

        for(v in dVector) {
            val nx = v.second + cur.second
            val ny = v.first + cur.first

            if(nx in 0 until w && ny in 0 until h && map[ny][nx] > 0) {
                map[ny][nx] -= 1
                if(map[ny][nx] == 0) {
                    q.add(Triple(ny,nx,cur.third+1))
                }
            }
        }
    }

    println(wave)
}