import kotlin.math.*

val curDice = mutableListOf(0,0,0,0,0,0)

fun main() {
    val firstInput = readln().split(" ").map { it.toInt() }
    val n = firstInput[0]
    val m = firstInput[1]
    val x = firstInput[2]
    val y = firstInput[3]
    val c = firstInput.last()

    val curLoc = mutableListOf(x,y)
    val map = MutableList(n) { MutableList(m) {0} }
    repeat(n) { i ->
        readln().split(" ").map { it.toInt() }.forEachIndexed { j, e -> map[i][j] = e}
    }
    val commandList = readln().split(" ").map { it.toInt() }
    commandList.forEach { commandDirection ->
        val direction = getNextDirectionPair(commandDirection)
        val nextX = direction.first + curLoc[0]
        val nextY = direction.second + curLoc[1]
        if(nextX in 0..<n && nextY in 0..<m) {
            val next = map[nextX][nextY]
            when(commandDirection) {
                1 -> moveEast()
                2 -> moveWest()
                3 -> moveNorth()
                else -> moveSouth()
            }
            if(next == 0) {
                map[nextX][nextY] = curDice[DiceDirection.BOTTOM.ordinal]
            }
            else {
                curDice[DiceDirection.BOTTOM.ordinal] = next
                map[nextX][nextY] = 0
            }
            curLoc[0] = nextX
            curLoc[1] = nextY
//            println(curLoc)
//            println(curDice)
            println(curDice[DiceDirection.TOP.ordinal])
        }
    }
}

enum class DiceDirection {
    TOP,
    SW,
    SE,
    SN,
    SS,
    BOTTOM
}

fun getNextDirectionPair(directionNumber: Int): Pair<Int, Int> {
    return when(directionNumber) {
        1 -> Pair(0,1)
        2 -> Pair(0,-1)
        3 -> Pair(-1,0)
        else -> Pair(1,0)
    }
}

fun moveNorth() {
    val prevStatus = curDice.map { it }
    curDice[DiceDirection.SN.ordinal] = prevStatus[DiceDirection.TOP.ordinal]
    curDice[DiceDirection.BOTTOM.ordinal] = prevStatus[DiceDirection.SN.ordinal]
    curDice[DiceDirection.SS.ordinal] = prevStatus[DiceDirection.BOTTOM.ordinal]
    curDice[DiceDirection.TOP.ordinal] = prevStatus[DiceDirection.SS.ordinal]
}

fun moveSouth() {
    val prevStatus = curDice.map { it }
    curDice[DiceDirection.SS.ordinal] = prevStatus[DiceDirection.TOP.ordinal]
    curDice[DiceDirection.TOP.ordinal] = prevStatus[DiceDirection.SN.ordinal]
    curDice[DiceDirection.SN.ordinal] = prevStatus[DiceDirection.BOTTOM.ordinal]
    curDice[DiceDirection.BOTTOM.ordinal] = prevStatus[DiceDirection.SS.ordinal]
}

fun moveEast() {
    val prevStatus = curDice.map { it }
    curDice[DiceDirection.SE.ordinal] = prevStatus[DiceDirection.TOP.ordinal]
    curDice[DiceDirection.BOTTOM.ordinal] = prevStatus[DiceDirection.SE.ordinal]
    curDice[DiceDirection.SW.ordinal] = prevStatus[DiceDirection.BOTTOM.ordinal]
    curDice[DiceDirection.TOP.ordinal] = prevStatus[DiceDirection.SW.ordinal]
}

fun moveWest() {
    val prevStatus = curDice.map { it }
    curDice[DiceDirection.SW.ordinal] = prevStatus[DiceDirection.TOP.ordinal]
    curDice[DiceDirection.TOP.ordinal] = prevStatus[DiceDirection.SE.ordinal]
    curDice[DiceDirection.SE.ordinal] = prevStatus[DiceDirection.BOTTOM.ordinal]
    curDice[DiceDirection.BOTTOM.ordinal] = prevStatus[DiceDirection.SW.ordinal]
}