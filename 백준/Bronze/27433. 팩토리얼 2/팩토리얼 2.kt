import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.*

//val bw = BufferedWriter(OutputStreamWriter(System.out))
//val br = BufferedReader(InputStreamReader(System.`in`))

fun main() {
    val n = readln().toLong()
    var result = 1L
    for(i in 1..n) {
        result *= i
    }
    println(result)
}

//fun bufferPrint(str: String) {
//    bw.write("$str\n")
//}
