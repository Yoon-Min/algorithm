import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val m = readLine().toInt()
    val linked = List(n+1) { mutableSetOf<Int>() }
    var isPossiblePath = true

    repeat(n) { idx ->
        val linkInfo = readLine().split(" ").map { it.toInt() }
        for(i in linkInfo.indices) {
            if(linkInfo[i] == 1) {
                linked[idx+1].add(i+1)
            }
        }
    }
    val path = readLine().split(" ").map { it.toInt() }
    
    for(i in 1 until path.size) {
        val s = path[i-1]
        val e = path[i]
        val visited = MutableList(n+1) { false }.also { it[s] = true }
        val q = ArrayDeque<Int>().also { it.add(s) }
        var isLinked = false

        while(q.isNotEmpty()) {
            val cur = q.removeFirst()

            if(cur == e) {
                isLinked = true
                break
            }

            linked[cur].forEach { next ->
                if(!visited[next]) {
                    visited[next] = true
                    q.add(next)
                }
            }
        }

        if(!isLinked) {
            isPossiblePath = false
            break
        }
    }

    if(isPossiblePath) println("YES")
    else println("NO")
}