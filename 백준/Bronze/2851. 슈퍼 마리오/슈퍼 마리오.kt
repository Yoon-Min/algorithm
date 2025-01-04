import kotlin.math.*

fun main() {
    var sum = 0
    for(i in 1..10) {
        val num = readln().toInt()
        val nextSum = sum + num
        if(nextSum >= 100) {
            if((100 - sum) >= abs(nextSum - 100) ) { println(nextSum) }
            else { println(sum) }
            break
        }
        sum = nextSum
        if(i == 10) { println(sum) }
    }
}

