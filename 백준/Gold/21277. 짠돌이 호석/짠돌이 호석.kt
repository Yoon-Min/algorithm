import kotlin.math.*

val searchMap = List(150) { MutableList(150) {0} }
var n1 = 0
var m1 = 0
var n2 = 0
var m2 = 0

fun isFit(c: Int, r: Int, map2: List<List<Int>>): Boolean {
    for(i in c until c+n2) {
        for(j in r until r+m2) {
            if(map2[i-c][j-r] == 1 && searchMap[i][j] == 1) {
                return false
            }
        }
    }
    return true
}

fun getRotatedMap(map: List<List<Int>>): List<List<Int>> {
    return (map.first().lastIndex downTo 0).map { j -> (0..map.lastIndex).map { i -> map[i][j] }}
}

fun main() = with(System.`in`.bufferedReader()) {
    var result = Int.MAX_VALUE
    readLine().split(" ").map { it.toInt() }.also {
        n1 = it[0]
        m1 = it[1]
    }
    val map1 = List(n1) { readLine().map { it.code - 48 } }
    readLine().split(" ").map { it.toInt() }.also {
        n2 = it[0]
        m2 = it[1]
    }
    var map2 = List(n2) { readLine().map { it.code - 48 } }

    for(i in 0 until n1) {
        for(j in 0 until m1) {
            searchMap[i+50][j+50] = map1[i][j]
        }
    }
    for (rotatedCount in 1..4) {
        for (i in 0 until 100) {
            for (j in 0 until 100) {
                if (isFit(i, j, map2)) {
                    val minCol = min(i, 50)
                    val maxCol = max(i+n2-1,50+n1-1)
                    val minRow = min(j, 50)
                    val maxRow = max(j+m2-1, 50+m1-1)
                    val w = maxCol - minCol + 1
                    val h = maxRow - minRow + 1
                    val area = w*h
                    result = min(result, area)
                }
            }
        }
        map2 = getRotatedMap(map2)
        n2 = map2.size
        m2 = map2.first().size
    }
    println(result)
}