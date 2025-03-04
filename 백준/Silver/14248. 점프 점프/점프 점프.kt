import kotlin.math.*

val n = readln().trim().toInt()
val bridge = readln().trim().split(" ").map { it.toInt() }
val s = readln().trim().toInt()
val isVisited = MutableList(n) { false }

fun main() {
    isVisited[s-1] = true
    dfs(s-1,1)
    println(isVisited.count { it})
}

fun dfs(s: Int, count: Int) {
    val nextStep = bridge[s]
    val nextLeft = s - nextStep
    val nextRight = s + nextStep
    if(nextLeft in bridge.indices) {
        isVisited[nextLeft] = true
        dfs(nextLeft, count+1)
    }
    if(nextRight in bridge.indices) {
        isVisited[nextRight] = true
        dfs(nextRight, count+1)
    }
}
