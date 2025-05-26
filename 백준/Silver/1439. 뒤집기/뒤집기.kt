import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val s = readLine()
    val sList = mutableListOf<Char>()

    var i = 0
    var prev = s[i]
    while(i < s.length) {
        if(s[i] != prev) {
            sList.add(prev)
            prev = s[i]
        }
        i += 1
    }
    if(sList.isNotEmpty() && sList.last() != prev) sList.add(prev)

    val zeroCnt = sList.count { it == '0' }
    val oneCnt = sList.size - zeroCnt

    println(min(zeroCnt,oneCnt))
}