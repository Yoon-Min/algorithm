import java.util.PriorityQueue
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

        val pq = PriorityQueue<Triple<Int,Int,Int>>(compareBy { it.third }).also { it.offer(Triple(0,0,map[0][0]))}
        while(pq.isNotEmpty()) {
            val cur = pq.poll()
            val curMoney = cur.third

            if(cur.first == n-1 && cur.second == n-1) {
                break
            }

            dVector.forEach { v ->
                val nx = v.second + cur.second
                val ny = v.first + cur.first

                if(nx in 0 until n && ny in 0 until n) {
                    if(curMoney + map[ny][nx] < money[ny][nx]) {
                        money[ny][nx] = curMoney + map[ny][nx]
                        pq.offer(Triple(ny,nx,money[ny][nx]))
                    }
                }
            }
        }

        println("Problem $cycle: ${money[n-1][n-1]}")
    }
}