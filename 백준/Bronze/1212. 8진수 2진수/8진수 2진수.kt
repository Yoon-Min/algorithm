import kotlin.math.*

fun main() {
    val n = readln()
    n.forEachIndexed { i, c ->
        val singleNum = c.code - 48
        val numToBinary = when(singleNum) {
            7 -> "111"
            6 -> "110"
            5 -> "101"
            4 -> "100"
            3 -> if(i == 0) "11" else "011"
            2 -> if(i == 0) "10" else "010"
            1 -> if(i == 0) "1" else "001"
            else -> if(i == 0) "0" else "000"
        }
        print(numToBinary)
    }
}