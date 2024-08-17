import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.*

//val bw = BufferedWriter(OutputStreamWriter(System.out))

fun main() {
    val result = mutableListOf<Int>()
    val n = readln().toInt()
    val a = readln().split(" ").map { it.toInt() }
    val b = readln().split(" ").map { it.toInt() }.toMutableList()
    val m = readln().toInt()
    val c = readln().split(" ").map { it.toInt() }

    val dq = ArrayDeque<Int>().apply {
        this.addAll(
            a.zip(b) { a, b -> Pair(a, b) }
                .filter { it.first == 0 }
                .map { it.second }
        )
    }
    c.forEach {
        dq.addFirst(it)
        result.add(dq.removeLast())
    }
    println(result.joinToString(" "))
}
