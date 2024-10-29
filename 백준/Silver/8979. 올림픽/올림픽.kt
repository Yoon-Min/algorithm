import kotlin.math.*

fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }
    val board = mutableListOf<Score>()
    repeat(n) {
        val (nation, g, s, b) = readln().split(" ").map { it.toInt() }
        board.add(Score(nation, g, s, b))
    }
    board.sortWith(compareBy({it.gold}, {it.silver}, {it.bronze}))
    board.reverse()
    board[0] = board.first().copy(rank = 1)
    var prev = board.first()
    for(i in 1..board.lastIndex) {
        val cur = board[i]
        if(cur.isEqual(prev)) {
            board[i] = cur.copy(rank = prev.rank)
        }
        else {
            board[i] = cur.copy(rank = prev.rank + 1)
        }
        prev = board[i]
    }
    println(board.find { it.nation == k}!!.rank)
}

data class Score(
    val nation: Int,
    val gold: Int,
    val silver: Int,
    val bronze: Int,
    val rank: Int = -1
) {
    fun isEqual(other: Score): Boolean {
        return gold == other.gold && silver == other.silver && bronze == other.bronze
    }
}


