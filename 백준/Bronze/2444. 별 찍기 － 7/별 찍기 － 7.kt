import java.util.*

fun main() {
    val n = readln().toInt()
    val starMap = MutableList(2*n-1) { MutableList(2*n-1) {" "} }
    val result = MutableList<String>(2*n-1) { "" }
    var start = 0
    var end = 2*n - 2
    while(start <= end) {
        val i = n - (start+1)
        val j = i + 2*start
        (i..j).forEach {
            starMap[start][it] = "*"
            starMap[end][it] = "*"
        }
        result[start] = starMap[start].slice(0..j).joinToString("")
        result[end] = starMap[end].slice(0..j).joinToString("")
        start++
        end--
    }
    result.forEach {
        println(it)
    }
}