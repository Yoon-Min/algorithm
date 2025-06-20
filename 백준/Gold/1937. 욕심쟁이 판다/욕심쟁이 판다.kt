import kotlin.math.*

val dVector = listOf(
    Pair(0, 1),
    Pair(1, 0),
    Pair(0, -1),
    Pair(-1, 0)
)

val dp = List(500) { MutableList(500) { 0 } }
val n = readln().toInt()
val map = List(n) { readln().split(" ").map { it.toInt() } }
var result = 0

fun main() = with(System.`in`.bufferedReader()) {
    for (i in 0 until n) {
        for (j in 0 until n) {
            if (dp[i][j] == 0) {
                dfs(i,j,1)
            }
        }
    }

    for (i in 0 until n) {
        for (j in 0 until n) {
            result = max(result, dp[i][j])
        }
    }

    println(result)
}

fun dfs(i: Int, j: Int, dist: Int) {

    var maxDist = dist

    for(v in dVector) {
        val nx = v.second + j
        val ny = v.first + i

        if(nx !in 0 until n || ny !in 0 until n) continue
        if(map[ny][nx] <= map[i][j]) continue

        if(dp[ny][nx] == 0) dfs(ny,nx,1)
        maxDist = max(maxDist, dist + dp[ny][nx])
    }

    dp[i][j] = maxDist
}