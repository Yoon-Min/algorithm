import kotlin.math.*

fun main() {
    val n = readln().toInt()
    repeat(n) { i ->
        val lineTotalLength = 2 * (i + 1) - 1
        val buffer = MutableList(lineTotalLength) { '*' }
        var innerBlankSize = lineTotalLength - 2
        var externalBlankSize = n - i - 1
        if (innerBlankSize < 0 || i == n - 1) {
            innerBlankSize = 0
        }
        if (innerBlankSize > 0) {
            (1..lineTotalLength-2).forEach { j -> buffer[j] = ' ' }
        }
        print(MutableList(externalBlankSize) { ' ' }.joinToString(""))
        println(buffer.joinToString(""))
    }
}

