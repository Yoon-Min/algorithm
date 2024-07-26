import java.util.*

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val a = MutableList(n) { mutableListOf<Int>() }
    val b = MutableList(n) { mutableListOf<Int>() }
    repeat(n) { i ->
        readln()
            .split(" ")
            .map { it.toInt() }
            .also { a[i] += it }
    }

    repeat(n) { i ->
        readln()
            .split(" ")
            .map { it.toInt() }
            .also { b[i] += it }
    }

    a.zip(b) { i, j -> i.zip(j) { k,l -> k+l } }.forEach { println(it.joinToString(" "))}

}