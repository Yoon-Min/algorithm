import kotlin.math.*

val map = mutableListOf<MutableList<Int>>()
val totalCheese = mutableSetOf<Pair<Int,Int>>()
val directionVector = listOf(listOf(0,1), listOf(1,0), listOf(0,-1), listOf(-1,0))

fun main() {
    val (n,m) = readln().split(" ").map { it.toInt() }
    repeat(n) { x ->
        val inputLine = readln().split(" ").map { it.toInt() }
        map.add(inputLine.toMutableList())
        for(y in inputLine.indices) {
            if(map[x][y] == 1) totalCheese.add(Pair(x,y))
        }
    }

    var hour = 0
    while(true) {
        if(totalCheese.size == 0) break
        updateAirSpace()
        updateTotalCheeseList()
        hour += 1
    }
    println(hour)
}

fun updateAirSpace() {
    val q = ArrayDeque<Pair<Int,Int>>().also { it.add(Pair(0,0)) }
    val isVisited = List(map.size) { MutableList(map[0].size) { false } }.also { it[0][0] = true }
    map[0][0] = -1

    while(q.isNotEmpty()) {
        val cur = q.removeFirst()
        directionVector.forEach { v ->
            val nextX = cur.first + v.first()
            val nextY = cur.second + v.last()
            val nextPos = Pair(nextX,nextY)
            if(isValidScope(nextPos) && !isVisited[nextX][nextY] && map[nextX][nextY] < 1) {
                map[nextX][nextY] = -1
                isVisited[nextX][nextY] = true
                q.addLast(nextPos)
            }
        }
    }
}

fun updateTotalCheeseList() {
    val disappearingCheeseList = mutableListOf<Pair<Int,Int>>()
    totalCheese.forEach { cheesePos ->
        var count = 0
        directionVector.forEach { v ->
            val nextX = cheesePos.first + v.first()
            val nextY = cheesePos.second + v.last()
            val nextPos = Pair(nextX, nextY)
            if(isValidScope(nextPos) && map[nextX][nextY] == -1) {
                count += 1
            }
        }
        if(count >= 2) {
            disappearingCheeseList.add(cheesePos)
        }
    }
    repeat(disappearingCheeseList.size) {
        val targetCheese = disappearingCheeseList.removeLast()
        totalCheese.remove(targetCheese)
        map[targetCheese.first][targetCheese.second] = 0
    }
}

fun isValidScope(pos: Pair<Int,Int>): Boolean {
    return pos.first in map.indices && pos.second in map[0].indices
}




