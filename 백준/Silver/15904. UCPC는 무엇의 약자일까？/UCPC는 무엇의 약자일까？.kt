import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val target = listOf('U', 'C', 'P', 'C')
    var targetPointer = 0
    var isValid = false
    val str = readLine()

    for(c in str) {
        if (c == target[targetPointer]) {
            targetPointer += 1
        }
        if (targetPointer == target.size) {
            isValid = true
            break
        }
    }

    if (isValid) println("I love UCPC")
    else println("I hate UCPC")
}