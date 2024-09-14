import kotlin.math.*

fun main() {
    val survey = mutableListOf(0,0)
    repeat(readln().toInt()) {
        val isCute = readln().toInt()
        survey[isCute]++
    }
    if(survey[1] > survey[0]) {
        println("Junhee is cute!")
    }
    else {
        println("Junhee is not cute!")
    }
}