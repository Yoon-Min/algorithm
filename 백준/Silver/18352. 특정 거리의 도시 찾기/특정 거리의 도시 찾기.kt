import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val sb = StringBuilder()
    val (n,m,k,x) = readLine().split(" ").map { it.toInt() }
    val linkedNode = List(n+1) { mutableListOf<Int>() }
    for(i in 0 until m) {
        val (a,b) = readLine().split(" ").map { it.toInt() }
        linkedNode[a].add(b)
    }

    val pq = ArrayDeque<Pair<Int,Int>>().also { it.add(Pair(0,x)) }
    val isVisited = MutableList(n+1) { false }.also { it[x] = true }
    val result = mutableListOf<Int>()
    while(pq.isNotEmpty()) {
        val cur = pq.removeFirst()
        for(next in linkedNode[cur.second]) {
            val nextDist = cur.first + 1
            if(isVisited[next]) continue
            else if(nextDist < k) {
                isVisited[next] = true
                pq.add(Pair(nextDist, next))
            }
            else if(nextDist == k) {
                isVisited[next] = true
                result.add(next)
            }
        }
    }
    result.sort()
    if(result.isEmpty()) println(-1)
    else {
        for(node in result) println(node)
    }
}

