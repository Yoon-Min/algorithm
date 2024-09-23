import kotlin.math.*

fun main() {
    var tmpStr = ""
    repeat(2) {
        tmpStr += getValue(readln()).first.toString()
    }
    val mulNum = getValue(readln()).second
    println(tmpStr.toLong() * mulNum)
}

fun getValue(color: String): Pair<Long,Long> {
    return when(color) {
        "black" -> Pair(0,1)
        "brown" -> Pair(1,10)
        "red" -> Pair(2,100)
        "orange" -> Pair(3,1000)
        "yellow" -> Pair(4,10000)
        "green" -> Pair(5,100000)
        "blue" -> Pair(6,1000000)
        "violet" -> Pair(7,10000000)
        "grey" -> Pair(8,100000000)
        else -> Pair(9,1000000000)
    }
}