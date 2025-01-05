import kotlin.math.*

fun main() {
    val t = readln().toInt()
    repeat(t) {
        val n = readln().toInt()
        val b = Integer.toBinaryString(n)
        val result = mutableListOf<Int>()
        b.reversed().forEachIndexed { i, num ->
            if(num.code - 48 == 1) {
                result.add(i)
            }
        }
        println(result.joinToString(" "))
    }
}

