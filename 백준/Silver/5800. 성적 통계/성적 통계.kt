import kotlin.math.*

fun main() {
    val k = readln().toInt()
    repeat(k) {
        val arr = readln().split(" ").map { it.toInt() }
        val score = arr.subList(1, arr.size).sortedDescending()
        val maxScore = score.max()
        val minScore = score.min()
        var maxDiff = 0

        for(i in 1 until score.size) {
            maxDiff = max(maxDiff, score[i-1] - score[i])
        }

        println("Class ${it+1}")
        println("Max $maxScore, Min $minScore, Largest gap $maxDiff")
    }
}