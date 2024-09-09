import kotlin.math.*

val nextDirection = listOf(listOf(0, 1), listOf(1, 0), listOf(0, -1), listOf(-1, 0))
val map = mutableListOf<MutableList<Int>>()
val isGroupMember = Array(50) { BooleanArray(50) }

fun main() {
    val (n, l, r) = readln().split(" ").map { it.toInt() }
    var nationCounter = 0
    var day = 0
    repeat(n) {
        val line = readln().split(" ").map { it.toInt() }
        map.add(line.toMutableList())
    }

    while (true) {
        val groupMember = MutableList(n) { MutableList(n) { false } }
        var associationCount = 0

        for (i in 0..<n) {
            for (j in 0..<n) {
                if (!groupMember[i][j]) {
                    val groupLocation = getGroupLocation(Pair(i, j), (l..r), groupMember)
                    if (groupLocation.isNotEmpty()) {
                        val association = groupLocation.map { map[it.first][it.second] }
                        val associationTotalPopulation = association.sumOf { it }
                        val associationSinglePopulation = associationTotalPopulation / association.size

                        groupLocation.forEach { p ->
                            map[p.first][p.second] = associationSinglePopulation
                        }
                        associationCount += 1
                    }
                }
            }
        }
        if (associationCount == 0) {
            break
        }
        day += 1
    }
    println(day)
}

fun getGroupLocation(
    start: Pair<Int, Int>,
    range: IntRange,
    groupMember: MutableList<MutableList<Boolean>>,
): List<Pair<Int, Int>> {
    val dq = ArrayDeque<Pair<Int, Int>>().also { it.add(start) }
    val groupLocation = mutableListOf(start)
    groupMember[start.first][start.second] = true

    while (dq.isNotEmpty()) {
        val cur = dq.removeFirst()
        val curNation = map[cur.first][cur.second]

        nextDirection.forEach { direction ->
            val nextI = cur.first + direction[0]
            val nextJ = cur.second + direction[1]

            if (nextI in (0..map.lastIndex) && nextJ in (0..map.lastIndex)) {
                val nextNation = map[nextI][nextJ]
                if (!groupMember[nextI][nextJ] && abs(curNation - nextNation) in range) {
                    val next = Pair(nextI, nextJ)
                    groupMember[next.first][next.second] = true
                    groupLocation.add(next)
                    dq.add(next)
                }
            }
        }
    }
    return if (groupLocation.size > 1) {
        groupMember[start.first][start.second] = true
        groupLocation
    } else listOf()
}
