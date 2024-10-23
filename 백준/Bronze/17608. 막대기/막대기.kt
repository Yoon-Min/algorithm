import kotlin.math.*

fun main() {
    val n = readln().toInt()
    val stick = mutableListOf<Int>()
    repeat(n) {
        val height = readln().toInt()
        stick.add(height)
    }
    var standardHeight = stick.last()
    var count = 1
    stick.reversed().forEach { curHeight ->
        if(curHeight > standardHeight) {
            standardHeight = curHeight
            count++
        }
    }
    println(count)
}

