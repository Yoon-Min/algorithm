import kotlin.math.*

fun main() {
    val (h, w) = readln().split(" ").map { it.toInt() }
    val blockHeight = readln().split(" ").map { it.toInt() }
    val map = List(h) { MutableList(w) {0} }
    var count = 0

    blockHeight.forEachIndexed { j, e ->
        for(i in h-1 downTo h-e) {
            map[i][j]  = 1
        }
    }

    for(i in map.lastIndex downTo 0 ) {
        var curIndex = 0
        while(curIndex < w) {
            val curNode = map[i][curIndex]
            if(curNode == 0 && curIndex > 0) {
                val prevNode = map[i][curIndex-1]
                if(prevNode == 1) {
                    val startingIndex = curIndex
                    while(curIndex < w && map[i][curIndex] == 0) {
                        curIndex += 1
                    }
                    if(curIndex == w) break
                    count += curIndex - startingIndex
                }
            }
            curIndex += 1
        }
    }
    println(count)
}



