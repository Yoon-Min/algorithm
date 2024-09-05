import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.*

fun main() {
    val t = readln().toInt()
    var curTime = t
    val timeUnit = listOf(300, 60, 10)
    val counter = mutableListOf<Int>(0, 0, 0)
    timeUnit.forEachIndexed { i, tUnit ->
        counter[i] += curTime / tUnit
        curTime %= tUnit
    }
    if(curTime == 0) println(counter.joinToString(" ")) else println(-1)
}