import kotlin.math.*

val dVector = listOf(Pair(0,1), Pair(1,0), Pair(-1,0), Pair(0,-1))

fun main() = with(System.`in`.bufferedReader()) {
    var cycle = 0

    while(true) {
        val n = readLine().toInt()
        if(n == 0) break

        cycle += 1

        val map = List(n) { readLine().split(" ").map { it.toInt() } }
        val money = List(n) { MutableList(n) { Int.MAX_VALUE } }.also { it[0][0] = map[0][0] }

        val q = ArrayDeque<Triple<Int,Int,Int>>().also { it.add(Triple(0,0,map[0][0])) }
        while(q.isNotEmpty()) {
            val cur = q.removeFirst()
            val curMoney = cur.third

            dVector.forEach { v ->
                val nx = v.second + cur.second
                val ny = v.first + cur.first

                if(nx in 0 until n && ny in 0 until n) {
                    if(curMoney + map[ny][nx] < money[ny][nx]) {
                        money[ny][nx] = curMoney + map[ny][nx]
                        q.add(Triple(ny,nx,money[ny][nx]))
                    }
                }
            }
        }

        println("Problem $cycle: ${money[n-1][n-1]}")
    }
}