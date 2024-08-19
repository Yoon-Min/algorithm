import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.*

val bw = BufferedWriter(OutputStreamWriter(System.out))
val br = BufferedReader(InputStreamReader(System.`in`))

fun main() {
    val counter = hashMapOf<String, Int>()
    val (n, m) = readln().split(" ").map { it.toInt() }
    repeat(n) {
        val word = br.readLine()
        if (word.length >= m) {
            if (!counter.containsKey(word)) {
                counter[word] = 1
            } else {
                counter[word] = counter[word]!! + 1
            }
        }
    }
    counter.toList().sortedWith(
        compareBy(
            {-1 * it.second},
            {-1 * it.first.length},
            {it.first}
        )
    ).forEach {
        bufferPrint(it.first)
    }
    bw.flush()
    bw.close()
}

fun bufferPrint(str: String) {
    bw.write("$str\n")
}
