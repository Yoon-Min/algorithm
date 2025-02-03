import kotlin.math.*
import kotlin.system.exitProcess

val timeChart = List(1000) { MutableList(366) {0} }
val dVector = listOf(Pair(0,1), Pair(1,0), Pair(0,-1), Pair(-1,0))

fun main() {
    var areaSum = 0
    val n = readln().toInt()
    val schedule = MutableList(400) { 0 }
    for (i in 0..<n) {
        val (s, e) = readln().split(" ").map { it.toInt() }
        schedule[s] += 1
        schedule[e+1] -= 1
    }
//    println(schedule)
    for(i in 1..schedule.lastIndex) {
        schedule[i] += schedule[i-1]
    }
//    println(schedule)

    var continuousCount = 0
    var height = 0
    for(i in 1..365) {
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
