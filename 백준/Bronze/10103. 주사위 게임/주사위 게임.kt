import kotlin.math.*

fun main() {
    var aScore = 100
    var bScore = 100
    repeat(readln().toInt()) {
        val (a,b) = readln().split(" ").map { it.toInt() }
        if(a < b) {
            aScore -= b
        }
        else if(a > b) {
            bScore -= a
        }
    }
    println(aScore)
    println(bScore)
}