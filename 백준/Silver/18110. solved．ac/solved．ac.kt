import kotlin.math.*

fun main() {
    val n = readln().toInt()
    val gradeList = mutableListOf<Int>()
    repeat(n) {
        val grade = readln().toInt()
        gradeList.add(grade)
    }
    if(gradeList.isEmpty()) {
        println(0)
    }
    else {
        gradeList.sort()
        val fifteenPercent = Math.round(gradeList.size * 0.15).toInt()
        val startIndex = fifteenPercent
        val lastIndex = gradeList.size - fifteenPercent - 1
        var sum = 0
        for(i in startIndex..lastIndex) {
            sum += gradeList[i]
        }
        println(Math.round(sum.toDouble() / (gradeList.size - fifteenPercent*2)).toInt())
    }
}

