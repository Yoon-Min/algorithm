fun main() = with(System.`in`.bufferedReader()) {
    val (n,m) = readLine().split(" ").map { it.toInt() }

    val linked = List(n+1) { mutableListOf<Int>() }
    val parent = MutableList(n+1) { 0 }

    repeat(m) {
        val (a,b) = readLine().split(" ").map { it.toInt() }
        linked[a].add(b)
        parent[b] += 1
    }

    val q = ArrayDeque<Int>()
    for(node in 1..n) {
        if(parent[node] == 0) q.add(node)
    }

    while(q.isNotEmpty()) {
        val cur = q.removeFirst()

        print("$cur ")

        linked[cur].forEach { next ->
            parent[next] -= 1
            if(parent[next] == 0) {
                q.add(next)
            }
        }
    }
}

