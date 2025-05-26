import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val w = mutableListOf<Int>()

    repeat(n) {
        w.add(readLine().toInt())
    }

    w.sortDescending()

    val maxW = w.mapIndexed { i,e -> e * (i+1) }.max()
    println(maxW)
}