import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.*

//val bw = BufferedWriter(OutputStreamWriter(System.out))
//val br = BufferedReader(InputStreamReader(System.`in`))
val state = mutableListOf<Int>()

fun main() {
    val str = readln().map { it.toString() }
    repeat(readln().toInt()) {
        val (a, b, c) = readln().split(" ")
        val counter = MutableList(str.size) { 0 }
        if(str[0] == a) counter[0] = 1
        for(k in 1..str.lastIndex) {
            counter[k] = if(str[k] == a) counter[k-1] + 1 else counter[k-1]
        }
        val i = b.toInt()
        val j = c.toInt()
        if(i == 0) println(counter[j])
        else println(counter[j] - counter[i-1])
    }
}

//fun bufferPrint(str: String) {
//    bw.write("$str\n")
//}
