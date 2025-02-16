import kotlin.math.*

val dVector = listOf(Pair(0,1), Pair(1,0), Pair(0,-1), Pair(-1,0))
val visited = mutableSetOf<Char>()
var result = 0

fun main() {
    val (r,c) = readln().split(" ").map { it.toInt() }
    val map = List(r) { readln().toList() }
    visited.add(map[0][0])
    dfs(map)
    println(result)
}

fun dfs(map: List<List<Char>>, s: Pair<Int,Int> = Pair(0,0), depth: Int = 1) {
    result = max(result, depth)
    dVector.forEach { v ->
        val nx = v.second + s.second
        val ny = v.first + s.first
        if(nx in map[0].indices && ny in map.indices && !visited.contains(map[ny][nx])) {
            visited.add(map[ny][nx])
            dfs(map, Pair(ny,nx), depth+1)
            visited.remove(map[ny][nx])
        }
    }
}
