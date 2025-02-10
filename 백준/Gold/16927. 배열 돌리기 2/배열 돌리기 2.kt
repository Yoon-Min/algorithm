
val dVector = listOf(Pair(1, 0), Pair(0, 1), Pair(-1, 0), Pair(0, -1))

fun main() {
    var (n, m, r) = readln().split(" ").map { it.toInt() }
    val map = List(n) { readln().split(" ").map { it.toInt() } }
    val resultMap = List(n) { MutableList(m) { 0 } }
    val isVisited = List(n) { MutableList(m) { false } }
    val total = n * m

    var mapSize = Pair(0..<n, 0..<m)
    var strokeLength = 2*m + 2*(n-2)
    var curNode = Pair(0, 0)
    var completed = 0
    var curDirection = 0
    while (completed < total) {
        val rotated = getRotatedPoint(r%strokeLength, curNode, curDirection, mapSize)
        resultMap[rotated.first][rotated.second] = map[curNode.first][curNode.second]
        isVisited[curNode.first][curNode.second] = true
        completed += 1

        var nextCol = curNode.first + dVector[curDirection].first
        var nextRow = curNode.second + dVector[curDirection].second
        val isInvalidScope = nextCol !in 0..<n || nextRow !in 0..<m

        if (isInvalidScope || isVisited[nextCol][nextRow]) {
            curDirection = (curDirection+1)%4
            nextCol = curNode.first + dVector[curDirection].first
            nextRow = curNode.second + dVector[curDirection].second
            if(curDirection == 0) {
                mapSize = mapSize.copy(
                    mapSize.first.first + 1..<mapSize.first.last,
                    mapSize.second.first + 1..<mapSize.second.last
                )
                strokeLength = 2*(mapSize.second.last - mapSize.second.first + 1) + 2*(mapSize.first.last - mapSize.first.first - 1)
            }
        }
        curNode = curNode.copy(nextCol, nextRow)
    }
    resultMap.forEach {
        println(it.joinToString(" "))
    }
}

fun getRotatedPoint(r: Int, node: Pair<Int, Int>, direction: Int, mapSize: Pair<IntRange, IntRange>): Pair<Int, Int> {
    if(r == 0) return node
    val col: Int
    val row: Int
    when(direction) {
        0 -> {
            col = node.first+r
            row = node.second
        }
        1 -> {
            col = node.first
            row = node.second+r
        }
        2 -> {
            col = node.first-r
            row = node.second
        }
        3 -> {
            col = node.first
            row = node.second-r
        }
        else -> throw IllegalArgumentException()
    }

    val colRange = mapSize.first
    val rowRange = mapSize.second
    if (col !in colRange || row !in rowRange) {
        val nextDirection = (direction+1)%4
        val nextNode: Pair<Int,Int>
        val nextR: Int
        when(direction) {
            0 -> {
                nextNode = Pair(colRange.last, rowRange.first)
                nextR = col - colRange.last
            }
            1 -> {
                nextNode = Pair(colRange.last, rowRange.last)
                nextR = row - rowRange.last
            }
            2 -> {
                nextNode = Pair(colRange.first, rowRange.last)
                nextR = colRange.first - col
            }
            3 -> {
                nextNode = Pair(colRange.first, rowRange.first)
                nextR = rowRange.first - row
            }
            else -> throw IllegalArgumentException()
        }
        return getRotatedPoint(nextR, nextNode, nextDirection, mapSize)
    }
    return Pair(col,row)
}