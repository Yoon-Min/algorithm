import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.*

fun main() {
    val n = readln().toInt()
    val seq = ArrayDeque<Int>().apply { this.addAll(readln().split(" ").map { it.toInt() }) }
    val sub = mutableListOf<Int>()
    var next = 1

    while (true) {
        if(seq.isEmpty() && sub.isEmpty()) break
        val i = seq.indexOf(next)
        if(i == -1 && sub.last() != next) break
        if(i != -1) {
            for (j in 0..i - 1) { sub.add(seq.removeFirst()) }
            seq.removeFirst()
        }
        else { sub.removeLast() }
        next++
    }
    if(seq.isEmpty() && sub.isEmpty()) println("Nice")
    else println("Sad")
}
