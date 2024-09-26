import kotlin.math.*

val cheeseNode = mutableSetOf<Pair<Int, Int>>()
val cheeseHoleNode = mutableSetOf<Pair<Int, Int>>()
val map = MutableList(100) { MutableList(100) { 0 } }
val nextDirection = listOf(listOf(-1, 0), listOf(0, 1), listOf(1, 0), listOf(0, -1))
var maxCol = 0
var maxRow = 0

fun main() {
    val (col, row) = readln().split(" ").map { it.toInt() }
    maxCol = col
    maxRow = row
    repeat(col) { i ->
        val inputList = readln().split(" ").map { it.toInt() }
        for (j in 0..<row) {
            map[i][j] = inputList[j]
            if (map[i][j] == 1) {
                cheeseNode.add(Pair(i, j))
            }
        }
    }

    initCheeseHoleStatus()

    var prevCheeseSize = cheeseNode.size
    var hour = 0
    while (cheeseNode.isNotEmpty()) {
        prevCheeseSize = cheeseNode.size
        val removalList = mutableListOf<Pair<Int, Int>>()

        cheeseNode.forEach { p ->
            nextDirection.forEach { d ->
                val nextX = p.first + d.first()
                val nextY = p.second + d.last()
                if (nextX in 0..<maxCol && nextY in 0..<maxRow && map[nextX][nextY] == 0) {
                    removalList.add(p)
                }
            }
        }
        removalList.forEach { p ->
            cheeseNode.remove(p)
            map[p.first][p.second] = 0
        }
        hour += 1
        updateCheeseHoleStatus()
//        printMapForTest()
    }
    println(hour)
    println(prevCheeseSize)
}

fun updateCheeseHoleStatus() {
    val isVisited = MutableList(100) { MutableList(100) { false } }
    val removalNode = mutableListOf<Pair<Int,Int>>()
    cheeseHoleNode.forEach { p ->
        if (!isVisited[p.first][p.second]) {
            nextDirection.forEach { d ->
                val nextX = p.first + d.first()
                val nextY = p.second + d.last()
                if (nextX in 0..<maxCol &&
                    nextY in 0..<maxRow &&
                    map[nextX][nextY] == 0 &&
                    !isVisited[nextX][nextY]
                ) {
                    isVisited[p.first][p.second] = true
                    removalNode.addAll(getUpdatedStatusWithBfs(p, isVisited))
                }
            }
        }
    }
    removalNode.forEach { p ->
        cheeseHoleNode.remove(p)
        map[p.first][p.second] = 0
    }
}

fun getUpdatedStatusWithBfs(start: Pair<Int, Int>, isVisited: MutableList<MutableList<Boolean>>): List<Pair<Int,Int>> {
    val q = ArrayDeque<Pair<Int, Int>>().also { it.add(start) }
    val tmpNode = mutableListOf<Pair<Int,Int>>().also { it.add(start) }

    while (q.isNotEmpty()) {
        val cur = q.removeFirst()
        nextDirection.forEach { d ->
            val nextX = cur.first + d.first()
            val nextY = cur.second + d.last()
            if (nextX in 0..<maxCol &&
                nextY in 0..<maxRow &&
                map[nextX][nextY] == 2 &&
                !isVisited[nextX][nextY]
            ) {
                isVisited[nextX][nextY] = true
                q.add(Pair(nextX,nextY))
                tmpNode.add(q.last())
            }
        }
    }
    return tmpNode
}

fun initCheeseHoleStatus() {
    val isVisited = MutableList(100) { MutableList(100) { false } }
    for (i in 0..<maxCol) {
        for (j in 0..<maxRow) {
            if (map[i][j] == 0 && !isVisited[i][j]) {
                isVisited[i][j] = true
                initWithBfs(Pair(i, j), isVisited)
            }
        }
    }
}

fun initWithBfs(start: Pair<Int, Int>, isVisited: MutableList<MutableList<Boolean>>) {
    val q = ArrayDeque<Pair<Int, Int>>().also { it.add(start) }
    var isCheeseHole = true
    val tmpZeroNode = mutableListOf<Pair<Int, Int>>().also { it.add(start) }

    while (q.isNotEmpty()) {
        val cur = q.removeFirst()
        nextDirection.forEach { d ->
            val nextX = cur.first + d.first()
            val nextY = cur.second + d.last()
            if (nextX in 0..<maxCol &&
                nextY in 0..<maxRow &&
                map[nextX][nextY] == 0 &&
                !isVisited[nextX][nextY]
            ) {
                isVisited[nextX][nextY] = true
                q.addLast(Pair(nextX, nextY))
                tmpZeroNode.add(q.last())
                if (nextX == 0 || nextX == maxCol-1) isCheeseHole = false
                if (nextY == 0 || nextY == maxRow-1) isCheeseHole = false
            }
        }
    }
    if (isCheeseHole) {
        tmpZeroNode.forEach { p ->
            map[p.first][p.second] = 2
            cheeseHoleNode.add(p)
        }
    }
}

fun printMapForTest() {
    map.slice(0..<maxCol).forEach {
        println(it.slice(0..<maxRow).joinToString(" "))
    }
    println()
}