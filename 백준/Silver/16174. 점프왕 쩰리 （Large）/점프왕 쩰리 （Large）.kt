import kotlin.system.exitProcess

val dVector = listOf(Pair(0,1), Pair(1,0))

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val map = List(n) { readLine().split(" ").map { it.toInt()} }
    val q = ArrayDeque<Pair<Int,Int>>().also { it.add(Pair(0,0)) }
    val isVisited = List(n) { MutableList(n) { false } }.also { it[0][0] = true }
    while(q.isNotEmpty()) {
        val cur = q.removeFirst()
        val nextStep = map[cur.first][cur.second]
        if(nextStep == -1) {
            println("HaruHaru")
            exitProcess(0)
        }
        dVector.forEach { v ->
            val nx = v.second * nextStep + cur.second
            val ny = v.first * nextStep + cur.first
            if(nx in 0 until n && ny in 0 until n && !isVisited[ny][nx]) {
                isVisited[ny][nx] = true
                q.add(Pair(ny,nx))
            }
        }
    }
    println("Hing")
}