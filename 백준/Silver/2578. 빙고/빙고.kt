import kotlin.math.*

fun main() {
    val map = List(5) { readln().split(" ").map { it.toInt()}.toMutableList() }
    val location = hashMapOf<Int, Pair<Int,Int>>()
    val q = ArrayDeque<Int>()

    repeat(5) {
        q.addAll(readln().split(" ").map { it.toInt() })
    }

    for(i in map.indices) {
        for(j in map.indices) {
            val number = map[i][j]
            val numberLocation = Pair(i,j)
            location[number] = numberLocation
        }
    }

    var removedNumberCount = 0
    var removedNumber = -1
    while(q.isNotEmpty()) {
        var removedLineCount = 0
        removedNumber = q.removeFirst()
        val numberLocation = location[removedNumber]!!
        map[numberLocation.first][numberLocation.second] = -1
        removedNumberCount += 1
        if(removedNumberCount > 4) {
            removedLineCount += map.count { i -> i.count { j -> j == -1 } == 5 }
            removedLineCount += (0..4).map { i -> (0..4).map { j -> map[j][i] } }.count { i -> i.count { it == -1} == 5 }
            removedLineCount += if((0..4).map { map[it][it] }.count { it == -1 } == 5) 1 else 0
            removedLineCount += if((0..4).map { map[4-it][it] }.count { it == -1 } == 5) 1 else 0
        }
        if(removedLineCount > 2) {
            break
        }
    }
    println(removedNumberCount)
}


