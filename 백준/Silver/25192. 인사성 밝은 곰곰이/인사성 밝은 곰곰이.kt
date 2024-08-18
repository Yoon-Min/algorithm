import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.*

//val bw = BufferedWriter(OutputStreamWriter(System.out))

fun main() {
    val n = readln().toInt()
    val chatLog = mutableListOf<String>()
    var result = 0

    repeat(n) { chatLog.add(readln()) }
    var i = 1
    var counter = 1
    while(counter < chatLog.size) {
        val s = mutableSetOf<String>()
        while(i < chatLog.size && chatLog[i] != "ENTER") {
            s.add(chatLog[i])
            i += 1
            counter += 1
        }
        result += s.size
        i += 1
        counter += 1
    }
    println(result)
}
