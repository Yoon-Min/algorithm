import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val g = readLine().toLong()
    val sb = StringBuilder()

    var left = 1L
    var right = 2L

    while(right < 100000) {
        val cal = right*right - left*left
        if(cal == g) {
            sb.append("$right\n")
        }
        if(cal > g) {
            left += 1
        }
        else {
            right += 1
        }
    }
    if(sb.isEmpty()) println(-1)
    else println(sb)
}
