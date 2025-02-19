import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n,m) = readLine().split(" ").map { it.toInt() }
    val (s,e) = readLine().split(" ").map { it.toInt() }
    val linked = List(n+1) { mutableListOf<Int>() }
    for(i in 0 until m) {
        val (a,b) = readLine().split(" ").map { it.toInt() }
        linked[a].add(b)
        linked[b].add(a)
    }

    val q = ArrayDeque<Pair<Int,Int>>().also { it.add(Pair(s, 0) )}
    val isVisited = MutableList(n+1) { false }.also { it[s] = true }
    while(q.isNotEmpty()) {
        val cur = q.removeFirst()
        if(cur.first == e) {
            println(cur.second)
            break
        }
        for(linkedN in linked[cur.first]) {
            if(!isVisited[linkedN]) {
                isVisited[linkedN] = true
                q.add(Pair(linkedN, cur.second+1))
            }
        }
        if(cur.first - 1 in 0..n && !isVisited[cur.first-1]) {
            isVisited[cur.first-1] = true
            q.add(Pair(cur.first-1, cur.second+1))
        }
        if(cur.first + 1 in 0..n && !isVisited[cur.first+1]) {
            isVisited[cur.first+1] = true
            q.add(Pair(cur.first+1, cur.second+1))
        }
    }
}

