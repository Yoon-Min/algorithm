import kotlin.math.*

val map = mutableListOf<MutableList<Char>>()
val directionVector = listOf(listOf(0,1), listOf(1,0), listOf(0,-1), listOf(-1,0)) // 0: East, 1: South, 2: West, 3: North
var holePos = Pair(0,0)

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    var redPos = Pair(0,0)
    var bluePos = Pair(0,0)
    repeat(n) { x ->
        val inputLine = readln().toMutableList()
        for(y in inputLine.indices) {
            if(inputLine[y] == 'R') redPos = redPos.copy(x, y)
            if(inputLine[y] == 'B') bluePos = bluePos.copy(x, y)
            if(inputLine[y] == 'O') holePos = holePos.copy(x, y)
        }
        map.add(inputLine)
    }
    map[redPos.first][redPos.second] = '.'
    map[bluePos.first][bluePos.second] = '.'
    println(bfs(redPos, bluePos))
}

// 1: horizontal, 2: vertical, 0: not same
fun isSameLine(a: Pair<Int,Int>, b: Pair<Int,Int>, direction: Int): Int {
    if(a.first == b.first) {
        if(direction == 0 || direction == 2) return 1
    }
    if(a.second == b.second) {
        if(direction == 1 || direction == 3) return 2
    }
    return 0
}

fun bfs(redPos: Pair<Int,Int>, bluePos: Pair<Int,Int>): Int {
    val q = ArrayDeque<Node>().also { it.add(Node(redPos, bluePos, 0)) }
    var minMovement = Int.MAX_VALUE
    while(q.isNotEmpty()) {
        val cur = q.removeFirst()
        if(cur.redPos == holePos) return cur.mCount
        if(cur.mCount == 10) continue

        directionVector.forEachIndexed { i, v ->
            var nextRedPos = getMovedPosition(i, cur.redPos)
            var nextBluePos = getMovedPosition(i, cur.bluePos)
            val checkLine = isSameLine(cur.redPos, cur.bluePos, i)
            if(checkLine > 0) {
                val prevPos = getPreviousNodeInTheSameLine(i, cur.redPos, cur.bluePos)
                val otherPos = if(prevPos == cur.redPos) cur.bluePos else cur.redPos
                val nextPrevPos = getMovedPosition(i, prevPos)
                val origin = map[nextPrevPos.first][nextPrevPos.second]
                if(origin != 'O') {
                    map[nextPrevPos.first][nextPrevPos.second] = 'T'
                }
                val nextOtherPos = getMovedPosition(i, otherPos)
                map[nextPrevPos.first][nextPrevPos.second] = origin
                nextRedPos = if(otherPos == cur.redPos) nextOtherPos else nextPrevPos
                nextBluePos = if(otherPos == cur.redPos) nextPrevPos else nextOtherPos
            }
            if(
                (cur.redPos != nextRedPos || cur.bluePos != nextBluePos) &&
                nextBluePos != holePos
            ) {
                q.addLast(Node(nextRedPos, nextBluePos, cur.mCount+1))
            }
        }
    }
    return -1
}

fun getPreviousNodeInTheSameLine(direction: Int, a: Pair<Int,Int>, b: Pair<Int,Int>): Pair<Int,Int> {
    return when(direction) {
        0 -> { if(a.second < b.second) b else a }
        1 -> { if(a.first < b.first) b else a }
        2 -> { if(a.second < b.second) a else b }
        3 -> { if(a.first < b.first) a else b }
        else -> throw IllegalArgumentException()
    }
}

fun getMovedPosition(direction: Int, startingPos: Pair<Int,Int>): Pair<Int,Int> {
    var nextPos = startingPos
    var prevPos = nextPos
    while(true) {
        prevPos = nextPos
        val nextX = directionVector[direction].first() + prevPos.first
        val nextY = directionVector[direction].last() + prevPos.second
        nextPos = prevPos.copy(nextX, nextY)

        if(map[prevPos.first][prevPos.second] == 'O') break
        if(!isValidScope(nextPos)) break
        if(map[nextPos.first][nextPos.second] != '.' && map[nextPos.first][nextPos.second] != 'O') break
    }
    return prevPos
}

fun isValidScope(pos: Pair<Int,Int>): Boolean {
    return pos.first in map.indices && pos.second in map[0].indices
}

data class Node(
    val redPos: Pair<Int,Int>,
    val bluePos: Pair<Int,Int>,
    val mCount: Int
)





