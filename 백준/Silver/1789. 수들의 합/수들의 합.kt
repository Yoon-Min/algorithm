import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val s = readLine().toLong()
    val tmp = sqrt((s*2).toDouble()).toLong()

    val result = if(tmp * (tmp+1) > 2*s) tmp -1 else tmp
    println(result)
}