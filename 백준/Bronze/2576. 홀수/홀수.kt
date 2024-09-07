import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.*

fun main() {
    val nList = mutableListOf<Int>()
    repeat(7) {
        val n = readln().toInt()
        nList.add(n)
    }
    nList
        .filter { it % 2 != 0 }
        .also { if(it.isNotEmpty()) println(it.sum()) else println(-1) }
        .also { if(it.isNotEmpty()) println(it.min()) }
}

