import kotlin.math.*
import kotlin.system.exitProcess

fun main() {
    // \ -> | -> / -> -
    repeat(readln().toInt()) {
        val (n, d) = readln().split(" ").map { it.toInt() }
        val directionVector = listOf(Pair(1, 1), Pair(1, 0), Pair(-1, 1), Pair(0, 1))
        val startingPoint = listOf(Pair(0, 0), Pair(0, n / 2), Pair(n - 1, 0), Pair(n / 2, 0))
        val map = mutableListOf<MutableList<Int>>()
        repeat(n) {
            map.add(readln().split(" ").map { it.toInt() }.toMutableList())
        }

        for (curAngle in 45..abs(d) step 45) {
            val directionList = List(4) { i ->
                val initialPos = map[startingPoint[i].first][startingPoint[i].second]
                mutableListOf(initialPos)
            }

            startingPoint.forEachIndexed { i, p ->
                var nextPos = p
                repeat(n - 1) {
                    val nextI = nextPos.first + directionVector[i].first
                    val nextJ = nextPos.second + directionVector[i].second
                    nextPos = Pair(nextI,nextJ)
                    directionList[i].add(map[nextI][nextJ])
                }
            }

            directionList.forEachIndexed { i, l ->
                val nextDirection = getNextDirection(i, d > 0)
                var nextPos = startingPoint[nextDirection]
                val inner = if((i == 1 && d > 0) ||(i ==2 && d < 0)) l.reversed() else l
                map[nextPos.first][nextPos.second] = inner.first()
                for(j in 1..< n) {
                    nextPos = nextPos.copy(
                        nextPos.first + directionVector[nextDirection].first,
                        nextPos.second + directionVector[nextDirection].second
                    )
                    map[nextPos.first][nextPos.second] =  inner[j]
                }
            }
        }
        printMap(map)
    }
}

fun getNextDirection(direction: Int, angleDirection: Boolean): Int {
    return if (angleDirection) {
        (direction + 1) % 4
    } else {
        if (direction == 0) 3 else direction - 1
    }
}

fun printMap(map: List<List<Int>>) {
    for (i in map.indices) {
        for (j in map[0].indices) {
            print("${map[i][j]} ")
        }
        println()
    }
}