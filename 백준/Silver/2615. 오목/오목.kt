import kotlin.math.*
import kotlin.system.exitProcess

val map = mutableListOf<List<Int>>()
var leftEdgePos: Pair<Int,Int>? = null
val directionVector = listOf(
    listOf(Pair(0,-1), Pair(0,1)), // -
    listOf(Pair(-1,0), Pair(1,0)), // |
    listOf(Pair(-1,-1), Pair(1,1)), // \
    listOf(Pair(1,-1), Pair(-1,1)), // /
)

fun main() {
    repeat(19) {
        map.add(readln().split(" ").map { it.toInt() } )
    }
    for(i in 0..18) {
        for(j in 0..18) {
            if(map[i][j] > 0) {
                val color = map[i][j]
                if(checkVictoryAtPosition(Pair(i,j), color)) {
                    println(color)
                    println("${leftEdgePos!!.first+1} ${leftEdgePos!!.second+1}")
                    exitProcess(0)
                }
            }
        }
    }
    println(0)
}

fun checkVictoryAtPosition(curPos: Pair<Int,Int>, color: Int): Boolean {
    directionVector.forEach { inner ->
        val leftLeftVector = inner.first()
        val rightVector = inner.last()
        val leftTotalCount = getContinuousLength(curPos, leftLeftVector, color)
        val rightTotalCount = getContinuousLength(curPos, rightVector, color)
        val counter = leftTotalCount + rightTotalCount - 1
        if(counter == 5) {
            leftEdgePos = getLeftEdgePosition(curPos, leftLeftVector, color)
            return true
        }
    }
    return false
}

fun getContinuousLength(startingPos: Pair<Int,Int>, directionVector: Pair<Int,Int>, color: Int): Int {
    var counter = 0
    var nextPos = startingPos
    while(isValidScope(nextPos) && map[nextPos.first][nextPos.second] == color) {
        counter += 1
        nextPos = nextPos.copy(nextPos.first + directionVector.first, nextPos.second + directionVector.second)
    }
    return counter
}

fun getLeftEdgePosition(startingPos: Pair<Int,Int>, leftDirectionVector: Pair<Int, Int>, color: Int): Pair<Int,Int> {
    var nextPos = startingPos
    while(map[nextPos.first][nextPos.second] == color) {
        val tmpNextPos = nextPos.copy(nextPos.first + leftDirectionVector.first, nextPos.second + leftDirectionVector.second)
        if(!isValidScope(tmpNextPos) || map[tmpNextPos.first][tmpNextPos.second] != color) {
            break
        }
        nextPos = tmpNextPos
    }
    return nextPos
}

fun isValidScope(pos: Pair<Int,Int>): Boolean {
    return pos.first in 0..18 && pos.second in 0..18
}