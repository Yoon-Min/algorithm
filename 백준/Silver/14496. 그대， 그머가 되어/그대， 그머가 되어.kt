
val dVector = listOf(Pair(0,1), Pair(1,0), Pair(0,-1), Pair(-1,0))

fun main() = with(System.`in`.bufferedReader()) {
    val (a,b) = readLine().split(" ").map { it.toInt() }
    val (n,m) = readLine().split(" ").map { it.toInt() }
    val linked = List(n+1) {mutableListOf<Int>()}
    val isVisited = MutableList(n+1) {false}
    repeat(m) {
        val (pairA, pairB) = readLine().split(" ").map { it.toInt() }
        linked[pairA].add(pairB)
        linked[pairB].add(pairA)
    }
    val q = ArrayDeque<Pair<Int,Int>>().also { it.add(Pair(a,0)) }
    isVisited[a] = true
    while(q.isNotEmpty()) {
        val cur = q.removeFirst()
        if(cur.first == b) {
            println(cur.second)
            return
        }
        linked[cur.first].forEach { next ->
            if(!isVisited[next]) {
                isVisited[next] = true
                q.add(Pair(next, cur.second+1))
            }
        }
    }
    println(-1)
}

