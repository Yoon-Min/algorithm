import kotlin.math.*

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val map = MutableList(n) { readln().split(" ").map { it.toInt() }.toMutableList() }
    val k = readln().toInt()

    for (i in 1..<n) {
        map[i][0] += map[i - 1][0]
    }
    for (i in 1..<m) {
        map[0][i] += map[0][i - 1]
    }
    for (c in 1..<n) {
        for (r in 1..<m) {
            map[c][r] += (map[c][r - 1] + map[c - 1][r] - map[c - 1][r - 1])
        }
    }

    repeat(k) {
        val (i, j, x, y) = readln().split(" ").map { it.toInt() - 1 }
        val area =
            if(i == 0 && j == 0) {
                map[x][y]
            }
            else if (0 < i && 0 < j) {
                map[x][y] - (map[x][j - 1] + map[i - 1][y] - map[i - 1][j - 1])
            } else if (j == 0) {
                map[x][y] - map[i-1][y]
            } else {
                map[x][y] - map[x][j-1]
            }
        println(area)
    }
}