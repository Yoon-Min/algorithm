import kotlin.system.exitProcess

val n = readln().toInt()
val dist = MutableList(n + 1) { -1 }
val linked = List(n + 1) { mutableListOf<Int>() }
var cycleCheck = false

fun main() = with(System.`in`.bufferedReader()) {
    repeat(n) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        linked[a].add(b)
        linked[b].add(a)
    }
    for(node in 1..n) {
        if(cycleCheck) break
        dfs(node, node)
    }
    bfs()
    (1..n).forEach  { print("${dist[it]} ")}
}

fun dfs(
    s: Int,
    target: Int,
    tmp: MutableList<Int> = mutableListOf(s),
    visited: MutableList<Boolean> = MutableList(n + 1) { false }.also { it[s] = true }
) {
//    println(s)
    for (nextNode in linked[s]) {
        if(tmp.size > 2 && nextNode == target) {
            tmp.forEach { node ->
                dist[node] = 0
            }
            cycleCheck = true
//            println("cycle: $tmp")
        }
        if (!visited[nextNode]) {
            visited[nextNode] = true
            tmp.add(nextNode)
            dfs(nextNode, target, tmp, visited)
            tmp.removeLast()
            visited[nextNode] = false
        }
    }
}

fun bfs() {
    val q = ArrayDeque<Pair<Int,Int>>().also {
        for(i in 1..n) {
            if(dist[i] == 0) it.add(Pair(i,0))
        }
    }
    val visited = MutableList(n+1) { false }.also {
        for(i in 1..n) {
            if(dist[i] == 0) it[i] = true
        }
    }

    while(q.isNotEmpty()) {
        val cur = q.removeFirst()
        val curNode = cur.first
        val nextDist = cur.second + 1

        for(nextNode in linked[curNode]) {
            if(!visited[nextNode] && dist[nextNode] == -1) {
                visited[nextNode] = true
                dist[nextNode] = nextDist
                q.add(Pair(nextNode,nextDist))
            }
        }
    }
}


