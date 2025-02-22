import java.util.*
import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n,m,r) = readLine().split(" ").map { it.toInt() }
    val totalItem = readLine().split(" ").map { it.toInt() }
    val linked = List(n+1) { mutableListOf<Int>() }
    val dist = List(n+1) { MutableList(n+1) {0} }
    var maxCount = 0
    repeat(r) {
        val (a,b,l) = readLine().split(" ").map { it.toInt() }
        linked[a].add(b)
        linked[b].add(a)
        dist[a][b] = l
        dist[b][a] = l
    }
    for(s in 1..n) {
        var count = 0
        for(e in 1..n) {
            val minDist = MutableList(n+1) {Int.MAX_VALUE}.also { it[s] = 0 }
            val pq = PriorityQueue<Pair<Int,Int>>(compareBy { it.second }).also { it.add(Pair(s, 0))}
            while(pq.isNotEmpty()) {
                val cur = pq.poll()
                val curTotalDist = cur.second
                if(cur.first == e) {
                    if(minDist[e] <= m) {
                        count += totalItem[e-1]
                    }
                    break
                }
                for(next in linked[cur.first]) {
                    if(dist[cur.first][next] + curTotalDist < minDist[next]) {
                        minDist[next] = dist[cur.first][next] + curTotalDist
                        pq.add(Pair(next, minDist[next]))
                    }
                }
            }
        }
        maxCount = max(maxCount, count)
    }
    println(maxCount)
}

