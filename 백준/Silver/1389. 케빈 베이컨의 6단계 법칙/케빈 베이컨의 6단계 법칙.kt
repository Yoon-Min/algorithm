import kotlin.math.*
import kotlin.system.exitProcess

fun main() {
    val (n,m) = readln().split(" ").map { it.toInt() }
    val map = List(n+1) { mutableSetOf<Int>() }
    val result = MutableList(n+1) { 0 }

    repeat(m) {
        val (a,b) = readln().split(" ").map { it.toInt() }
        map[a].add(b)
        map[b].add(a)
    }
    for(curN in 1..n) {
        val q = ArrayDeque<Pair<Int,Int>>().also { it.add(Pair(curN,0))}
        val isVisited = MutableList(n+1) { false }

        isVisited[curN] = true
        var count = 0
        while(q.isNotEmpty()) {
            val cur = q.removeFirst()
            count += cur.second
            map[cur.first].forEach { neighbor ->
                if(!isVisited[neighbor]) {
                    isVisited[neighbor] = true
                    q.add(Pair(neighbor, cur.second+1))
                }
            }
        }
        result[curN] = count
    }
    var min = Int.MAX_VALUE
    var resultNum = Int.MAX_VALUE
    for(curN in 1..n) {
        val curResult = result[curN]
        if(curResult < min) {
            min = curResult
            resultNum = curN
        }
        else if(curResult == min) {
            resultNum = min(resultNum, curN)
        }
    }
    println(resultNum)
}


data class Node(
    val alphabet: Char,
    val index: Int,
    val order: Int
)




