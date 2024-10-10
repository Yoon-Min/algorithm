import kotlin.math.*

fun main() {
    val n = readln().toInt()
    val result = readln().split(" ").map { it.toInt() }
    var bonus = 1
    var totalScore = 0
    result.forEach { s ->
        if(s == 0) {
            bonus = 1
        }
        else {
            totalScore += bonus
            bonus += 1
        }
    }
    println(totalScore)
}
