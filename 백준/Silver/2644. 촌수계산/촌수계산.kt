import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val (targetP, targetC) = readLine().split(" ").map { it.toInt() }
    val linked = List(n+1) { mutableListOf<Int>() }
    repeat(readLine().toInt()) {
        val (x,y) = readLine().split(" ").map { it.toInt() }
        linked[x].add(y)
        linked[y].add(x)
    }
    val q = ArrayDeque<Pair<Int,Int>>().also { it.add(Pair(targetP,0)) }
    val isVisited = MutableList(n+1) { false }.also { it[targetP] = true }
    var linkedCount = -1
    while(q.isNotEmpty()) {
        val cur = q.removeFirst()
        if(cur.first == targetC) {
            linkedCount = cur.second
            break
        }
        linked[cur.first].forEach { next ->
            if(!isVisited[next]) {
                isVisited[next] = true
                q.add(Pair(next, cur.second+1))
            }
        }
    }
    println(linkedCount)
}

