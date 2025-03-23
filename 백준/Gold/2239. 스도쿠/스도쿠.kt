import kotlin.system.exitProcess

var empty = mutableListOf<Pair<Int, Int>>()
val map = List(9) { readln().map { it.code - 48 }.toMutableList() }.also {
    for(i in 0 until 9) {
        for(j in 0 until 9) {
            if(it[i][j] == 0) empty.add(Pair(i,j))
        }
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    dfs(0, empty.size)
}

fun dfs(s: Int, totalEmpty: Int) {
    if (totalEmpty == 0) {
        map.forEach { println(it.joinToString("")) }
        exitProcess(0)
    }
    val pos = empty[s]
    (1..9).forEach { n ->
        if (isValidNumInPos(pos, n)) {
            map[pos.first][pos.second] = n
            dfs(s+1, totalEmpty - 1)
            map[pos.first][pos.second] = 0
        }
    }
}

fun isValidNumInPos(pos: Pair<Int, Int>, num: Int): Boolean {
    if (map[pos.first].contains(num)) return false
    (0..8).forEach { i ->
        if (map[i][pos.second] == num) return false
    }
    val s = pos.toSquarePosition()
    for (i in s.first until s.first + 3) {
        for (j in s.second until s.second + 3) {
            if (map[i][j] == num) return false
        }
    }
    return true
}

fun Pair<Int, Int>.toSquarePosition(): Pair<Int, Int> {
    val i = this.first
    val j = this.second
    return when (i) {
        in 0 until 3 -> {
            when (j) {
                in 0 until 3 -> Pair(0, 0)
                in 3 until 6 -> Pair(0, 3)
                else -> Pair(0, 6)
            }
        }

        in 3 until 6 -> {
            when (j) {
                in 0 until 3 -> Pair(3, 0)
                in 3 until 6 -> Pair(3, 3)
                else -> Pair(3, 6)
            }
        }

        else -> {
            when (j) {
                in 0 until 3 -> Pair(6, 0)
                in 3 until 6 -> Pair(6, 3)
                else -> Pair(6, 6)
            }
        }
    }
}