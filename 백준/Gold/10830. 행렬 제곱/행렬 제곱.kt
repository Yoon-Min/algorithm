import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.*

fun main() {
    val (n, b) = readln().split(" ").map { it.toLong() }
    val m = mutableListOf<List<Long>>()
    repeat(n.toInt()) {
        m.add(readln().split(" ").map { it.toLong() })
    }
    powMatrix(m, b).forEach {
        println(it.joinToString(" "))
    }
}

fun powMatrix(matrix: List<List<Long>>, n: Long): List<List<Long>> {
    if(n == 1L) return matrix.modAllElement(1000)
    val half = powMatrix(matrix, n/2)
    var result = multiplyMatrix(half, half)
    if(n%2 == 1L) result =  multiplyMatrix(result, matrix)
    return result
}

fun multiplyMatrix(a: List<List<Long>>, b: List<List<Long>>): List<List<Long>> {
    val result = MutableList(a.size) { MutableList(b.first().size) { -1L } }
    val transformB = transpose(b)
    for(i in a.indices) {
        for(j in transformB.indices) {
            result[i][j] = a[i].zip(transformB[j]) { ae, be -> ae*be%1000 }.sum() % 1000
        }
    }
    return result
}

fun List<List<Long>>.modAllElement(m: Int = 1000): List<List<Long>> {
    return this.map { a -> a.map { b -> b%m }}
}

fun <T> transpose(matrix: List<List<T>>): List<List<T>> {
    return matrix[0].indices.map { matrix.map { l -> l[it] } }
}