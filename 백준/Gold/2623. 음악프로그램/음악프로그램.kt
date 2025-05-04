import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n,m) = readLine().split(" ").map { it.toInt() }
    val linked = List(n+1) { mutableListOf<Int>() }
    val parent = MutableList(n+1) { 0 }

    repeat(m) {
        val seq = readLine().split(" ").map { it.toInt() }
        for(i in 1 until seq.lastIndex) {
            val parentNode = seq[i]
            val childNode = seq[i+1]
            linked[parentNode].add(childNode)
            parent[childNode] += 1
        }
    }

    val q = ArrayDeque<Int>()
    val result = mutableListOf<Int>()

    for(i in 1..n) {
        if(parent[i] == 0) {
            q.add(i)
        }
    }

    while(q.isNotEmpty()) {
        val cur = q.removeFirst()
        result.add(cur)

        linked[cur].forEach { next ->
            parent[next] -= 1
            if(parent[next] == 0) {
                q.add(next)
            }
        }
    }

    if(result.size != n) {
        println(0)
    }
    else {
        result.forEach { println(it) }
    }
}