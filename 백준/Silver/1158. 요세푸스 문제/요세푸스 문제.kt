import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.*

fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }
    val result = mutableListOf<Int>()
    val state = ArrayDeque<Int>()
    state.addAll(List(n) { it+1 })

    (1..n).forEach {
        for(i in 1..k-1) {
            state.addLast(state.removeFirst())
        }
        result.add(state.removeFirst())
    }
    print("<${result.joinToString(", ")}>")
}