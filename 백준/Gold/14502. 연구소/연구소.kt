import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.*

val combinations = mutableListOf<List<Int>>()
val map = mutableListOf<List<Int>>()
val emptyList = mutableListOf<Pair<Int, Int>>()
val virusList = mutableListOf<Pair<Int, Int>>()

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    var result = 0

    repeat(n) {
        map.add(readln().split(" ").map { it.toInt() })
    }

    for (i in 0..<n) {
        for (j in 0..<m) {
            if (map[i][j] == 0) {
                emptyList.add(Pair(i, j))
            }
            if(map[i][j] == 2) {
                virusList.add(Pair(i, j))
            }
        }
    }

    combination(arr = Array(emptyList.size) { it } , n = emptyList.size, r = 3)
    combinations.forEach { p ->
        val mutableMap = map.map { it.toMutableList() }.toMutableList()
        p.forEach { i ->
            val point = emptyList[i]
            mutableMap[point.first][point.second] = 1
        }
        result = max(result, getTotalSafeZone(mutableMap))
    }
    println(result)
}

fun combination(
    arr: Array<Int>,
    n: Int,
    r: Int,
    nowArr: Array<Int> = arr.sliceArray(0 until r),
    curSelect: Int = 0,
    start: Int = 0
) {

    if (r == curSelect) {
        combinations.add(nowArr.toList())
        return
    }

    for (i in start until arr.size) {
        nowArr[curSelect] = arr[i]
        combination(arr, n, r, nowArr, curSelect + 1, i + 1)
    }
}

fun getTotalSafeZone(map: MutableList<MutableList<Int>>): Int {
    virusList.forEach { p ->
        bfs(p, map)
    }
    return map.sumOf { it.count { e -> e == 0 } }
}

fun bfs(start: Pair<Int,Int>, map: MutableList<MutableList<Int>>) {
    val q = ArrayDeque<Pair<Int, Int>>()
    val nextDestinationX = listOf(0, 1, 0, -1)
    val nextYDestinationY = listOf(1, 0, -1, 0)

    q.addLast((start))
    while(q.isNotEmpty()) {
        val cur = q.removeFirst()
        for(i in 0..3) {
            val nextX = cur.first + nextDestinationX[i]
            val nextY = cur.second + nextYDestinationY[i]
            val checkNextXY = nextX in (0..map.lastIndex) && nextY in (0..map.first().lastIndex)
            if(checkNextXY && map[nextX][nextY] == 0) {
                map[nextX][nextY] = 2
                q.addLast(Pair(nextX, nextY))
            }
        }
    }
}