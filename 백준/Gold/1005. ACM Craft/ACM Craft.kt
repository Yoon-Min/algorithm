import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    repeat(readLine().toInt()) {
        val (n, k) = readLine().split(" ").map { it.toInt() }
        val buildingTime = readLine().split(" ").map { it.toInt() }
        val linked = List(n + 1) { mutableListOf<Int>() }
        val totalParent = MutableList(n + 1) { 0 }
        val dp = MutableList(n+1) {0}

        repeat(k) {
            val (x, y) = readLine().split(" ").map { it.toInt() }
            linked[x].add(y)
            totalParent[y] += 1
        }
        val w = readLine().toInt()
        val root = mutableListOf<Int>()
        totalParent.forEachIndexed { node, cnt -> if(node != 0 && cnt == 0) root.add(node) }
        val q = ArrayDeque<Int>().also { it.addAll(root) }
        root.forEach {
            dp[it] = buildingTime[it-1]
        }

        while(q.isNotEmpty()) {
            val cur = q.removeFirst()
            linked[cur].forEach { next ->
                dp[next] = max(dp[next], dp[cur] + buildingTime[next-1])
                if(--totalParent[next] == 0) {
                    q.add(next)
                }
            }
        }
        println(dp[w])
    }
}

