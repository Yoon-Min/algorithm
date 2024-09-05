import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.*

/*
현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
현재 칸의 주변
$4$칸 중 청소되지 않은 빈 칸이 없는 경우,
바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
현재 칸의 주변
$4$칸 중 청소되지 않은 빈 칸이 있는 경우,
반시계 방향으로
$90^\circ$ 회전한다.
바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
1번으로 돌아간다.
 */
val map = mutableListOf<MutableList<Int>>()
val nextDirection = listOf(Pair(-1, 0), Pair(0, 1), Pair(1, 0), Pair(0, -1))

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val (startX, startY, startDirection) = readln().split(" ").map { it.toInt() }
    var curNode = ClearBot(startX, startY, startDirection)
    var clearCounter = 0

    repeat(n) {
        val line = readln().split(" ").map { it.toInt() }.toMutableList()
        map.add(line)
    }
    while (true) {
        val cur = curNode
        val x = cur.x
        val y = cur.y
        val direction = cur.direction

        if (map[x][y] == 0) {
            map[x][y] = 2
            clearCounter += 1
        }

        var isExistNotClearedArea = false
        nextDirection.forEach { d ->
            val nextX = x + d.first
            val nextY = y + d.second
            if (isValidLocation(nextX, nextY) && map[nextX][nextY] == 0) {
                isExistNotClearedArea = true
            }
        }

        if (!isExistNotClearedArea) { // 주변 4방향중 청소가 필요한(0) 공간이 존재하지 않음 ( only 1 or 2 )
            val behindDirection = getBehindDirection(direction)
            val nextDirectionSet = nextDirection[behindDirection]
            val tmpX = nextDirectionSet.first + x
            val tmpY = nextDirectionSet.second + y
            if (isValidLocation(tmpX, tmpY)) {
                curNode = ClearBot(tmpX, tmpY, direction)
                continue
            } else {
                break
            }
        } else {
            val reversedDirectionOfClock = getReversedDirectionOfClock(direction)
            val nextDirectionSet = nextDirection[reversedDirectionOfClock]
            val tmpX = nextDirectionSet.first + x
            val tmpY = nextDirectionSet.second + y
            curNode = if (isValidLocation(tmpX, tmpY) && map[tmpX][tmpY] == 0) {
                ClearBot(tmpX, tmpY, reversedDirectionOfClock)
            } else {
                curNode.copy(direction = reversedDirectionOfClock)
            }
        }
    }

    println(clearCounter)
}

fun isValidLocation(x: Int, y: Int): Boolean {
    return x in (0..map.lastIndex) && y in (0..map.first().lastIndex) && map[x][y] != 1
}

fun getBehindDirection(curDirection: Int): Int {
    return when (curDirection) {
        0 -> 2
        1 -> 3
        2 -> 0
        else -> 1
    }
}

fun getReversedDirectionOfClock(curDirection: Int): Int {
    return when (curDirection) {
        0 -> 3
        1 -> 0
        2 -> 1
        else -> 2
    }
}

data class ClearBot(
    val x: Int,
    val y: Int,
    val direction: Int
)

