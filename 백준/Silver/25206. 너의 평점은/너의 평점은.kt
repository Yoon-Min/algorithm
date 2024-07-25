import java.util.*

fun main() {
    val gradeMap = mapOf(
        "A+" to 4.5,
        "A0" to 4.0,
        "B+" to 3.5,
        "B0" to 3.0,
        "C+" to 2.5,
        "C0" to 2.0,
        "D+" to 1.5,
        "D0" to 1.0,
        "F" to 0.0
    )
    var sum = 0.0
    var creditSum = 0.0
    repeat(20) {
        val (lecture, credit, grade) = readln().split(" ")
        if(grade != "P") {
            sum += credit.toDouble() * gradeMap[grade]!!
            creditSum += credit.toDouble()
        }
    }
    println(String.format("%.6f", sum/creditSum))
}