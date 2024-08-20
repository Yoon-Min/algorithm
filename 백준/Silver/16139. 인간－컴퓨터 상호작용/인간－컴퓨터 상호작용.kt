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
    val str = br.readLine()
    val dp = MutableList(26) { MutableList(str.length) { 0 } }

    dp.forEachIndexed { i, l ->
        for(j in 0..str.lastIndex) {
            if(str[j].code == i + 97) {
                l[j]++
            }
            if(j > 0) l[j] += l[j-1]
        }
    }

    repeat(br.readLine().toInt()) {
        val (a, b, c) = br.readLine().split(" ")
        val i = b.toInt()
        val j = c.toInt()
        val code = a.toCharArray().first().code - 97
        if(dp[code].last() == 0) bufferPrint("0")
        else if(i == 0) bufferPrint("${dp[code][j]}")
        else bufferPrint("${dp[code][j] - dp[code][i-1]}")
    }
    bw.flush()
    bw.close()
}

fun bufferPrint(str: String) {
    bw.write("$str\n")
}
