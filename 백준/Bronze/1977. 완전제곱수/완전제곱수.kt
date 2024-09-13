import kotlin.math.*

val sawtooth = mutableListOf<MutableList<Int>>(mutableListOf())

fun main() {
    val n = readln().toDouble()
    val m = readln().toInt()
    val minPerfectSquareNumber = ceil(sqrt(n)).pow(2)
    var cur = minPerfectSquareNumber
    var sum = 0
    while(cur <= m) {
        sum += cur.toInt()
        cur = (sqrt(cur) + 1).pow(2)
    }

    if(sum == 0) {
        println(-1)
    }
    else {
        println(sum)
        println(minPerfectSquareNumber.toInt())
    }

}