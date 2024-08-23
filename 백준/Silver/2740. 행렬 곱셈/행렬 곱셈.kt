import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.*

val bw = BufferedWriter(OutputStreamWriter(System.out))
val br = BufferedReader(InputStreamReader(System.`in`))

fun bufferPrint(str: String) {
    bw.write("$str\n")
}

fun main() {
    val (n, m1) = readln().split(" ").map { it.toInt() }
    val a = mutableListOf<List<Int>>()
    repeat(n) {
        a.add(readln().split(" ").map { it.toInt() })
    }
    val (m2, k) = readln().split(" ").map { it.toInt() }
    val b = mutableListOf<List<Int>>()
    repeat(m2) {
        b.add(readln().split(" ").map { it.toInt() })
    }

    val tp = transpose(b)
    a.map { a ->
        tp.map { b -> b.zip(a).sumOf { c -> c.first * c.second } }
    }.forEach {
        println(it.joinToString(" "))
    }
}

fun transpose(matrix: MutableList<List<Int>>): List<List<Int>> {
    return matrix[0].indices.map { matrix.map { l -> l[it] } }
}