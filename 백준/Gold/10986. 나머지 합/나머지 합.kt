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
    val (n, m) = readln().split(" ").map { it.toLong() }
    val l = readln().split(" ").map { it.toLong() }
    var sum = l.first()
    val infoList = mutableListOf<Info>()
    var result = 0L

    for (i in 0..l.lastIndex) {
        if(i > 0) {
            sum += l[i]
        }
        infoList.add(Info(l[i], sum, sum % m))
        if (infoList.last().m == 0L) result++
    }

    val mCnt = infoList.groupBy { it.m }.toList().map { Pair(it.first, it.second.size) }
    result += mCnt.sumOf {
        val cnt = it.second.toLong()
        cnt * (cnt - 1) / 2
    }
    println(result)
}

data class Info(
    val e: Long,
    val s: Long,
    val m: Long
)


fun bufferPrint(str: String) {
    bw.write("$str\n")
}
