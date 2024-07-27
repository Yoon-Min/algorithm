import java.util.*

fun main() {
    val arr = MutableList(101) { MutableList(101) { 0 } }
    repeat(readln().toInt()) {
        val (x, y) = readln().split(" ").map { it.toInt() }
        for(i in y..y+9) {
            for(j in x..x+9) {
                arr[i][j] = 1
            }
        }
    }
    println(arr.sumOf { it.sum() })
}