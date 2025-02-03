import kotlin.math.*
import kotlin.system.exitProcess

val timeChart = List(1000) { MutableList(366) {0} }
val dVector = listOf(Pair(0,1), Pair(1,0), Pair(0,-1), Pair(-1,0))

fun main() {
    var areaSum = 0
    val n = readln().toInt()
    val schedule = MutableList(366) { 0 }
    for (i in 0..<n) {
        val (s, e) = readln().split(" ").map { it.toInt() }
        for(j in s..e) { schedule[j] += 1}
    }

    var continuousCount = 0
    var height = 0
    for(i in 1..schedule.lastIndex) {
        if(schedule[i] > 0) {
            continuousCount += 1
            height = max(height, schedule[i])
        }
        else if(schedule[i] == 0) {
            areaSum += continuousCount * height
            continuousCount = 0
            height = 0
        }
    }
    areaSum += height * continuousCount
    println(areaSum)
}
