import kotlin.math.*

var joinCount = 0

fun main() = with(System.`in`.bufferedReader()) {

    repeat(readLine().toInt()) {
        val n = readLine().toInt()
        val arr = readLine().split(" ").map { it.toInt() - 1 }
        val linked = List(n) { mutableListOf<Int>() }

        arr.forEachIndexed { a, b ->
            linked[a].add(b)
            linked[b].add(a)
        }

        val visited = MutableList(n) { false }
        val finished = MutableList(n) { false }
        joinCount = 0

        for (i in 0 until n) {
            if (!visited[i]) {
                dfs(i,arr,visited, finished)
            }
        }
        println(n-joinCount)
    }
}

fun dfs(
    s: Int,
    arr: List<Int>,
    visited: MutableList<Boolean>,
    finished:  MutableList<Boolean>,
) {
    visited[s] = true
    val next = arr[s]

    if(!visited[next]) {
        dfs(next,arr,visited,finished)
    }
    else if(!finished[next]) {
        var cur = next
        do {
            joinCount += 1
            cur = arr[cur]
        } while(cur != next)
    }
    finished[s] = true
}