import kotlin.math.*
import kotlin.system.exitProcess

fun main() {
    val n = readln().toInt()
    val p = readln().split(" ").map { it.toInt() }
    val result = p.toMutableList()
    var i = p.lastIndex-1
    var prev = p.last()

    while(i > -1 && p[i] < prev) {
        prev = p[i]
        i -= 1
    }
    if(i == -1) {
        println(-1)
        return
    }
    val standard = i
    i = p.lastIndex
    while(i > standard && p[i] > p[standard]) {
        i -= 1
    }
    result[standard] = p[i]
    result[i] = p[standard]
    for(index in 0.. standard) {
        print("${result[index]} ")
    }
    println(result.slice(standard+1..<n).sortedDescending().joinToString(" "))
}