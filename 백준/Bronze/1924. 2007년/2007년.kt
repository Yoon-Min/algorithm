import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.*

fun main() {
    val (x,y) = readln().split(" ").map { it.toInt() }
    val daysOfMonth = listOf(0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
    val dayOfWeek = Week.entries.toList()
    val weekLength = dayOfWeek.size
    var result = 0

    for(m in 1..x) {
        val days = daysOfMonth[m]
        val startDay = if(m == 1) 2 else 1
        val endDay = if(m == x) y else days
        for(d in startDay..endDay) {
           result = (result+1)%weekLength
        }
    }
    println(dayOfWeek[result])
}

enum class Week {
    MON, TUE, WED, THU, FRI, SAT, SUN
}