import java.util.PriorityQueue
import kotlin.math.*
val neighbor = List(1001) { mutableListOf<Pair<Int,Int>>() }

fun main() {
    val (n,m,x) = readln().split(" ").map { it.toInt() }
    var max = 0
    repeat(m) {
        val (u,v,t) = readln().split(" ").map { it.toInt() }
        neighbor[u].add(Pair(v,t))
    }

    for(s in 1..n) {
        val prev = dstra(s, x)
        val after = dstra(x, s)
        if(prev+after > max) {
            max = prev+after
        }
    }
    println(max)
}

fun dstra(s: Int, target: Int): Int {
    val dist = MutableList(1001) { Int.MAX_VALUE }
    var result = Int.MAX_VALUE
    val q = PriorityQueue<Pair<Int,Int>>(compareBy { it.second })
    q.add(Pair(s,0))
    dist[s] = 0
    while(q.isNotEmpty()) {
        val node = q.poll()
        if(node.first == target) {
            result = dist[target]
            break
        }
        neighbor[node.first].forEach { p ->
            val next = p.first
            val nextDist = p.second + node.second
            if(nextDist < dist[next]) {
                dist[next] = nextDist
                q.add(Pair(next, nextDist))
            }
        }
    }
    return result
}
