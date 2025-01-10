import kotlin.math.*

val map = mutableListOf<List<Int>>()
val isRampInstalled = List(100) { MutableList(100) { false } }
val directionVectorMap = hashMapOf(
    1 to listOf(listOf(0,1), listOf(0,-1)), // horizontal
    2 to listOf(listOf(1,0), listOf(-1,0)) // vertical
)

fun main() {
    var possiblePathCount = 0
    val (n, l) = readln().split(" ").map { it.toInt() }
    repeat(n) { map.add(readln().split(" ").map { it.toInt() }) }
    for(i in map.indices) {
        if(isPathPossible(Pair(0,i), 2, l)){
            possiblePathCount += 1
        }
        repeat(n) { j -> isRampInstalled[j][i] = false }
        if(isPathPossible(Pair(i,0), 1, l)) {
            possiblePathCount += 1
        }
        repeat(n) { j -> isRampInstalled[i][j] = false }
    }
    println(possiblePathCount)
}

fun isPathPossible(initPos: Pair<Int, Int>, orientation: Int, l: Int): Boolean {
    val directionVector = directionVectorMap[orientation]!!.first()
    var prevPos = initPos
    var curPos = initPos.copy(initPos.first + directionVector[0], initPos.second + directionVector[1])
    while (isValidScope(curPos)) {
        val curHeight = map[curPos.first][curPos.second]
        val prevHeight = map[prevPos.first][prevPos.second]
        if (curHeight - prevHeight == 1) { // reverse search
            val rDirectionVector = directionVectorMap[orientation]!!.last()
            if(isRampInstallable(prevPos, rDirectionVector, l)) {
                var tmpPos = prevPos
                repeat(l) {
                    isRampInstalled[tmpPos.first][tmpPos.second] = true
                    tmpPos = tmpPos.copy(tmpPos.first + rDirectionVector[0], tmpPos.second + rDirectionVector[1])
                }
            }
            else return false
        } else if (prevHeight - curHeight == 1) { // forward search
            if(isRampInstallable(curPos, directionVector, l)) {
                repeat(l) {
                    prevPos = curPos
                    curPos = curPos.copy(curPos.first + directionVector[0], curPos.second + directionVector[1])
                    isRampInstalled[prevPos.first][prevPos.second] = true
                }
                continue
            }
            else return false
        } else if(abs(curHeight - prevHeight) > 1) return false // can't install a ramp
        prevPos = curPos
        curPos = curPos.copy(curPos.first + directionVector[0], curPos.second + directionVector[1])
    }
    return true
}

fun isRampInstallable(startingPos: Pair<Int, Int>, directionVector: List<Int>, l: Int): Boolean {
    val height = map[startingPos.first][startingPos.second]
    var curPos = startingPos
    var count = 0
    while (true) {
        if (isValidScope(curPos) &&
            map[curPos.first][curPos.second] == height &&
            !isRampInstalled[curPos.first][curPos.second]
        ) {
            count += 1
            curPos = curPos.copy(curPos.first + directionVector.first(), curPos.second + directionVector.last())
        }
        else if(count < l) {
            return false
        }
        if(count == l) {
            break
        }
    }
    return true
}

fun isValidScope(pos: Pair<Int, Int>): Boolean {
    return pos.first in map.indices && pos.second in map[0].indices
}





