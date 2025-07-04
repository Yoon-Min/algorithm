import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val (l,r) = readLine().split(" ")
    var eightCounter = 0
    
    if (l.length != r.length) {
        println(eightCounter)
        return
    }

    for (i in l.indices) {
        val leftNum = l[i].code - 48
        val rightNum = r[i].code - 48
        if (leftNum == rightNum) {
            if (leftNum == 8) {
                eightCounter += 1
            }
        }
        else break
    }
    println(eightCounter)
}