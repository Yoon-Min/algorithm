import kotlin.math.*

val dVector = mapOf(
    1 to Pair(0, -1),
    2 to Pair(-1, 0),
    4 to Pair(0, 1),
    8 to Pair(1, 0)
)

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val map = List(m) { readLine().split(" ").map { it.toInt() }.toMutableList() }
    var maxArea = 0

    bfs(n, m, map).also {
        println(it.second)
        println(it.first)
        maxArea = it.first
    }

    for (i in 0 until m) {
        for (j in 0 until n) {

            for (b in 0 until 4) {
                val direction = 2.0.pow(b).toInt()
                if (map[i][j] and direction != direction) continue

                map[i][j] -= direction
                maxArea = max(maxArea, bfs(n, m, map, true, Pair(i,j)).first)
                map[i][j] += direction
            }
        }
    }
    println(maxArea)
}

fun bfs(
    n: Int,
    m: Int,
    map: List<MutableList<Int>>,
    isBroken: Boolean = false,
    s: Pair<Int, Int>? = null
): Pair<Int, Int> {

    var maxArea = 0
    var groupCount = 0

    val visited = List(m) { MutableList(n) { false } }

    val colRange = if(s != null) s.first..s.first else 0 until m
    val rowRange = if(s != null) s.second..s.second else 0 until n

    for (i in colRange) {
        for (j in rowRange) {
            if (!visited[i][j]) {
                visited[i][j] = true
                val q = ArrayDeque<Pair<Int, Int>>().also { it.add(Pair(i, j)) }
                var area = 1

                while (q.isNotEmpty()) {
                    val cur = q.removeFirst()

                    for (b in 0 until 4) {
                        val direction = 2.0.pow(b).toInt()
                        if (map[cur.first][cur.second] and direction == direction) continue

                        val nx = cur.second + dVector.getValue(direction).second
                        val ny = cur.first + dVector.getValue(direction).first

                        if (nx in 0 until n && ny in 0 until m && !visited[ny][nx]) {
                            visited[ny][nx] = true
                            area += 1
                            q.add(Pair(ny, nx))
                        }
                    }
                }
                maxArea = max(maxArea, area)
                groupCount += 1
                if (isBroken) return Pair(maxArea, groupCount)
            }
        }
    }
    return Pair(maxArea, groupCount)
}