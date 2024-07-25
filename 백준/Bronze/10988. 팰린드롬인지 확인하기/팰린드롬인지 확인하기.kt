import java.util.*

fun main() {
    val str = readln()
    var start = 0
    var end = str.lastIndex
    while(start <= end && str[start] == str[end]) {
        start++
        end--
    }
    println(if(start > end) 1 else 0)
}