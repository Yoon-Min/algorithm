fun main() = with(System.`in`.bufferedReader()) {
    val (n,m,x) = readLine().split(" ").map { it.toInt() }
    val child = List(n+1) { mutableListOf<Int>() }
    val parent = List(n+1) { mutableListOf<Int>() }

    repeat(m) {
        val (a,b) = readLine().split(" ").map { it.toInt() }
        child[a].add(b)
        parent[b].add(a)
    }

    var topCount = 0
    var bottomCount = 0
    val visited = MutableList(n+1) { false }.also { it[x] = true }
    val q = ArrayDeque<Int>().also { it.add(x) }

    while(q.isNotEmpty()) {
        val cur = q.removeFirst()
        child[cur].forEach { next ->
            if(!visited[next]) {
                visited[next] = true
                bottomCount += 1
                q.add(next)
            }
        }
    }

    visited.fill(false)
    visited[x] = true
    q.clear()
    q.add(x)

    while(q.isNotEmpty()) {
        val cur = q.removeFirst()
        parent[cur].forEach { next ->
            if(!visited[next]) {
                visited[next] = true
                topCount += 1
                q.add(next)
            }
        }
    }

    val highestRank = topCount + 1
    val lowestRank = n - bottomCount
    println("$highestRank $lowestRank")
}

