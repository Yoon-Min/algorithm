import kotlin.math.*

val n = readln().toInt()
val dVector = listOf(Pair(0,1), Pair(1,0), Pair(0,-1), Pair(-1,0))
val emptySpace = mutableSetOf<Pair<Int,Int>>()
val placementMap = List(n) { MutableList(n) {0} }

fun main() {
    val totalStudent = n*n
    val preferredStudent = List(totalStudent+1) { mutableListOf<Int>() }
    val sequence = mutableListOf<Int>()
    repeat(totalStudent) {
        val input = readln().split(" ").map { it.toInt() }
        preferredStudent[input.first()].addAll(input.slice(1..input.lastIndex))
        sequence.add(input.first())
    }
    for(i in 0 until n) {
        for(j in 0 until n) {
            emptySpace.add(Pair(i,j))
        }
    }
    for(studentNum in sequence) {
        val filtered = mutableSetOf<Point>()
        // cond 1.
        for(pos in emptySpace) {
            var count = 0
            for(v in dVector) {
                val ny = pos.first + v.first
                val nx = pos.second + v.second
                if(ny in 0 until n && nx in 0 until n && placementMap[ny][nx] > 0 && preferredStudent[studentNum].contains(placementMap[ny][nx])) {
                    count += 1
                }
            }
            filtered.add(Point(pos, count))
        }
        val adjacentMaxPoint = filtered.maxBy { it.count }
        filtered.removeAll { it.count < adjacentMaxPoint.count }
        if(filtered.size == 1) {
            setStudentOnMap(filtered.first().location, studentNum)
            continue
        }

        // count 2.
        val filteredEmptyMax = mutableSetOf<Point>()
        for(point in filtered) {
            var count = 0
            for(v in dVector) {
                val ny = point.location.first + v.first
                val nx = point.location.second + v.second
                if(ny in 0 until n && nx in 0 until n && placementMap[ny][nx] == 0) {
                   count += 1
                }
            }
            filteredEmptyMax.add(point.copy(count = count))
        }
        val maxEmptyPoint = filteredEmptyMax.maxBy { it.count }
        filteredEmptyMax.removeAll { it.count < maxEmptyPoint.count }
        if(filteredEmptyMax.size == 1) {
            setStudentOnMap(filteredEmptyMax.first().location, studentNum)
            continue
        }

        // cond 3.
        val minColPoint = filteredEmptyMax.minBy { it.location.first }
        filteredEmptyMax.removeAll { it.location.first > minColPoint.location.first }
        if(filteredEmptyMax.size == 1) {
            setStudentOnMap(filteredEmptyMax.first().location, studentNum)
            continue
        }

        val minRowPoint = filteredEmptyMax.minBy { it.location.second }
        filteredEmptyMax.removeAll { it.location.second > minRowPoint.location.second }
        setStudentOnMap(filteredEmptyMax.first().location, studentNum)
    }

//    printMap()

    var satisfactionScore = 0
    for(i in 0 until n) {
        for(j in 0 until n) {
            val studentNum = placementMap[i][j]
            var count = 0
            for(v in dVector) {
                val ny = i + v.first
                val nx = j + v.second
                if(ny in 0 until n && nx in 0 until n && preferredStudent[studentNum].contains(placementMap[ny][nx])) {
                    count += 1
                }
            }
            satisfactionScore += getSatisfactionScore(count)
        }
    }
    println(satisfactionScore)
}

fun setStudentOnMap(pos: Pair<Int,Int>, studentNum: Int) {
    placementMap[pos.first][pos.second] = studentNum
    emptySpace.remove(pos)
}

fun getSatisfactionScore(adjacent: Int): Int {
    return when(adjacent) {
        0 -> 0
        1 -> 1
        2 -> 10
        3 -> 100
        4 -> 1000
        else -> throw IllegalArgumentException()
    }
}

fun printMap() {
    for(i in 0 until n) {
        println(placementMap[i].joinToString(" "))
    }
    println()
}

data class Point(
    val location: Pair<Int,Int>,
    val count: Int
)


