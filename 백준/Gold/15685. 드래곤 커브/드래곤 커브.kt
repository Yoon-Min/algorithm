import kotlin.math.*

val dragonCurvePos = mutableSetOf<Pair<Int,Int>>()
val map = List(100) { MutableList(100) {0} }
val directionVector = listOf(listOf(0,1), listOf(-1,0), listOf(0,-1), listOf(1,0))

fun main() {
    val n = readln().toInt()
    val infoList = mutableListOf<List<Int>>()
    repeat(n) {
        val info = readln().split(" ").map { it.toInt() }
        infoList.add(info)
    }

    infoList.forEach { info ->
        val startingPoint = Pair(info[1], info[0])
        val startingDirection = info[2]
        val targetDragonGeneration = info[3]
        val directionList = mutableListOf(startingDirection)
        var curEndPos = Point(true, getEndPosFromStartingPoint(startingPoint, startingDirection))
        var curDragonGeneration = 0

        dragonCurvePos.add(startingPoint)
        //map[startingPoint.first][startingPoint.second] = 1
        if(targetDragonGeneration == 0) {
            dragonCurvePos.add(curEndPos.position)
            //map[curEndPos.position.first][curEndPos.position.second] = 1
        }
        else {
            while(curDragonGeneration < targetDragonGeneration) {
                val cloneDirectionList = directionList.map { it }
                for(i in cloneDirectionList.lastIndex downTo 0) {
                    dragonCurvePos.add(curEndPos.position)
                    //map[curEndPos.position.first][curEndPos.position.second] = 1
                    val curDirection = cloneDirectionList[i]
                    val nextDirection = getNextDirection(curDirection)
                    curEndPos = if(curEndPos.isEndPoint) {
                        Point(false, getStartingPosFromEndPoint(curEndPos.position, nextDirection))
                    } else {
                        Point(true, getEndPosFromStartingPoint(curEndPos.position, nextDirection))
                    }
                    directionList.add(nextDirection)
                    dragonCurvePos.add(curEndPos.position)
                    //map[curEndPos.position.first][curEndPos.position.second] = 1
                }
                curDragonGeneration += 1
            }
        }


    }

//    var countA = 0
//    for(i in map.indices) {
//        println(map[i].joinToString(" "))
//        for(j in map.indices) {
//            if(map[i][j] == 1 && isSquareA(Pair(i,j))) {
//                countA += 1
//            }
//        }
//    }
//    println(countA)
//    println()

    var countB = 0
    dragonCurvePos.forEach { p ->
        if(isSquareB(p)) countB += 1
    }
    println(countB)
}

fun getNextDirection(direction: Int): Int {
    return when(direction) {
        0 -> 3
        1 -> 0
        2 -> 1
        else -> 2
    }
}

fun getEndPosFromStartingPoint(startingPoint: Pair<Int,Int>, direction: Int): Pair<Int,Int> {
    val nextX = startingPoint.first + directionVector[direction].first()
    val nextY = startingPoint.second + directionVector[direction].last()
    return Pair(nextX, nextY)
}

fun getStartingPosFromEndPoint(endPoint: Pair<Int,Int>, direction: Int): Pair<Int,Int> {
    val nextX = endPoint.first - directionVector[direction].first()
    val nextY = endPoint.second - directionVector[direction].last()
    return Pair(nextX, nextY)
}

fun isSquareA(standardPos: Pair<Int,Int>): Boolean {
    val a = standardPos
    val b = standardPos.copy(second = standardPos.second+1)
    val c = standardPos.copy(first = standardPos.first+1)
    val d = standardPos.copy(standardPos.first+1, standardPos.second+1)
    return map[a.first][a.second] == 1 &&
            map[b.first][b.second] == 1 &&
            map[c.first][c.second] == 1 &&
            map[d.first][d.second] == 1
}

fun isSquareB(standardPos: Pair<Int,Int>): Boolean {
    val a = standardPos
    val b = standardPos.copy(second = standardPos.second+1)
    val c = standardPos.copy(first = standardPos.first+1)
    val d = standardPos.copy(standardPos.first+1, standardPos.second+1)
    return dragonCurvePos.contains(a) &&
            dragonCurvePos.contains(b) &&
            dragonCurvePos.contains(c) &&
            dragonCurvePos.contains(d)
}

data class Point(
    val isEndPoint: Boolean,
    val position: Pair<Int,Int>
)




