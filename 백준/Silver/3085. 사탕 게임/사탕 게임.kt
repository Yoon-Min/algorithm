import kotlin.math.*

var max = 0

fun main() {
    val n = readln().toInt()
    val map = List(n) { readln().toMutableList() }

    for(i in map.indices) {
        for(j in map.indices) {
            val colNext = i+1
            val rowNext = j+1
            if(colNext in map.indices) {
                val a = Pair(i,j)
                val b = Pair(colNext,j)
                swap(a, b, map)
                max = max(max, getMaxLineLength(map))
                swap(a, b, map)
            }
            if(rowNext in map.indices) {
                val a = Pair(i,j)
                val b = Pair(i,rowNext)
                swap(a, b, map)
                max = max(max, getMaxLineLength(map))
                swap(a, b, map)
            }
        }
    }
    println(max)
}

fun swap(a: Pair<Int,Int>, b: Pair<Int,Int>, map: List<MutableList<Char>>) {
    val tmp = map[a.first][a.second]
    map[a.first][a.second] = map[b.first][b.second]
    map[b.first][b.second] = tmp
}

fun getMaxLineLength(map: List<List<Char>>): Int {
    var tmpColMax = 0
    var tmpRowMax = 0
    // find column max line
    for(i in map.indices) {
        var colPrev = map[i][0]
        var counter = 0
        map[i].forEach { cur ->
            if (colPrev == cur) {
                counter += 1
            } else {
                counter = 1
                colPrev = cur
            }
            tmpColMax = max(tmpColMax, counter)
        }
    }
    // find row max line
    for(j in map.indices) {
        var rowPrev = map[0][j]
        var counter = 0
        for(i in map.indices) {
            val cur = map[i][j]
            if (rowPrev == cur) {
                counter += 1
            } else {
                counter = 1
                rowPrev = cur
            }
            tmpRowMax = max(tmpRowMax, counter)
        }
    }
    // return
    return max(tmpRowMax, tmpColMax)
}
