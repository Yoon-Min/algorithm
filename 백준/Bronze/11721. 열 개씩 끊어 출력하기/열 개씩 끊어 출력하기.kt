import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.*

fun main() {
    val n = readln()
    var counter = 0
    var startIndex = 0
    while(counter < n.length) {
        val endIndex = if((startIndex+9) > n.lastIndex) n.lastIndex else startIndex+9
        (startIndex..endIndex).forEach { print(n[it]) }.also { println() }
        counter += (endIndex - startIndex + 1)
        startIndex = endIndex+1
    }
}
