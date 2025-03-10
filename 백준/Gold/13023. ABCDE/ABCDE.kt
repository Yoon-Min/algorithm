
import kotlin.math.*
import kotlin.system.exitProcess

val linked = List(2000) { mutableListOf<Int>() }
val isVisited = MutableList(2000) { false }

fun main() = with(System.`in`.bufferedReader()) {
    val (n,m) = readLine().split(" ").map { it.toInt() }
    repeat(m) {
        val (a,b) = readLine().split(" ").map { it.toInt() }
        linked[a].add(b)
        linked[b].add(a)
    }

    for(cur in 0 until n) {
        isVisited[cur] = true
        dfs(0, cur)
        isVisited[cur] = false
    }
    println(0)
}

fun dfs(relation: Int, s: Int) {
    if(relation == 4) {
        println(1)
        exitProcess(0)
    }
    linked[s].forEach { next ->
        if(!isVisited[next]) {
            isVisited[next] = true
            dfs(relation+1, next)
            isVisited[next] = false
        }
    }
}
