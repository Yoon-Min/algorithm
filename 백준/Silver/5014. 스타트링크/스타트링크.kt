import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val input = readLine().split(" ").map { it.toInt() }
    val f = input[0] // 총 층수
    val s = input[1] // 현재 위치
    val g = input[2] // 스타트링크 위치
    val u = input[3] // 위층으로 u만큼 이동
    val d = input[4] // 아래층으로 d만큼 이동
    val isVisited = MutableList(f+1) { false }.also { it[s] = true }
    val q = ArrayDeque<Pair<Int,Int>>().also { it.add(Pair(s,0)) }
    var result = Int.MAX_VALUE
    while(q.isNotEmpty()) {
        val cur = q.removeFirst()
        if(cur.first == g) {
            result = cur.second
            break
        }
        val up = if(u + cur.first > f) cur.first else u + cur.first
        val down = if(cur.first - d < 1) cur.first else cur.first - d
        if(!isVisited[up] && up != cur.first) {
            isVisited[up] = true
            q.add(Pair(up, cur.second+1))
        }
        if(!isVisited[down] && down != cur.first) {
            isVisited[down] = true
            q.add(Pair(down, cur.second+1))
        }
    }
    if(result == Int.MAX_VALUE) println("use the stairs")
    else println(result)
}

