
val dVector = listOf(Pair(0,1), Pair(1,0), Pair(0,-1), Pair(-1,0))

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val m = readLine().toInt()
    val linked = List(n+1) {mutableListOf<Int>()}
    val isVisited = MutableList(n+1) {false}
    repeat(m) {
        val (a,b) = readLine().split(" ").map { it.toInt() }
        linked[a].add(b)
        linked[b].add(a)
    }
    val q = ArrayDeque<Pair<Int,Int>>().also { it.add(Pair(1,0)) }
    var count = 0
    isVisited[1] = true
    while(q.isNotEmpty()) {
        val cur = q.removeFirst()
        linked[cur.first].forEach { friend ->
            if(!isVisited[friend] && cur.second < 2) {
                isVisited[friend] = true
                count += 1
                q.add(Pair(friend, cur.second+1))
            }
        }
    }
    println(count)
}

