import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.*

fun main() {
    val (totalMusic, avg) = readln().split(" ").map { it.toInt() }
    val maxMelody = totalMusic * avg
    var curMelody = maxMelody
    while(ceil(curMelody.toDouble() / totalMusic) == avg.toDouble()) {
        curMelody -= 1
    }
    println(curMelody+1)
}

