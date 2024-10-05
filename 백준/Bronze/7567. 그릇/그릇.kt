import kotlin.math.*

fun main() {
    val input = readln()
    var prev = if(input.first() == ')') '(' else ')'
    var totalHeight = 0
    input.forEach { b ->
        totalHeight += if(prev == b) {
            5
        } else {
            10
        }
        prev = b
    }
    println(totalHeight)
}

