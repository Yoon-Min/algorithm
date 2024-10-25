import kotlin.math.*

fun main() {
    val map = List(101) { MutableList(101) {0} }
    repeat(4) {
        val (a,b,c,d) = readln().split(" ").map { it.toInt() }
        val colRange = if(b > d) d..<b else b..<d
        val rowRange = if(a > c) c..<a else a..<c
        for(i in colRange) {
            for(j in rowRange) {
                map[i][j] = 1
            }
        }
    }
    println(map.sumOf { it.sum() })

}

