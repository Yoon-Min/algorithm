import kotlin.math.*

val dVector = listOf(Pair(0,1), Pair(1,0), Pair(0,-1), Pair(-1,0))

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val map = List(n) { readLine().map { it.code - 48 xor 1 } }
    val removedBlack = List(n) { MutableList(n) {Int.MAX_VALUE} }.also { it[0][0] = 0 }
    val q = ArrayDeque<Triple<Int,Int,Int>>().also { it.add(Triple(0,0,0)) }

    while(q.isNotEmpty()) {
        val cur = q.removeFirst()
        for(v in dVector) {
            val nx = v.second + cur.second
            val ny = v.first + cur.first
            if(nx !in 0 until n || ny !in 0 until n) continue
            val nextRemovedBlack = cur.third + map[ny][nx]
            if(nextRemovedBlack < removedBlack[ny][nx]) {
                removedBlack[ny][nx] = nextRemovedBlack
                q.add(Triple(ny,nx,nextRemovedBlack))
            }
        }
    }
//    removedBlack.forEach { println(it.joinToString(""))}
    println(removedBlack[n-1][n-1])
}

