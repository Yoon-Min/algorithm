import kotlin.math.*

fun main() {
    val m = readln().toInt()
    val cupIndex = mutableListOf(0,1,2,3)
    repeat(m) {
        val (x,y) = readln().split(" ").map { it.toInt() }
        val xIndex = cupIndex[x]
        val yIndex = cupIndex[y]
        cupIndex[x] = yIndex
        cupIndex[y] = xIndex
    }
    cupIndex.forEachIndexed { index, i ->  if(i == 1) println(index) }
}

