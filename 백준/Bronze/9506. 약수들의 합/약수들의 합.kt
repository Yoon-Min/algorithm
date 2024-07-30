import java.util.*
import kotlin.math.*

fun main() {
    while(true) {
        val n = readln().toInt()
        if(n == -1) break

        val result = mutableSetOf(1)
        var sum = 1
        for(curN in 2..sqrt(n.toDouble()).toInt()) {
            if(n % curN == 0) {
                sum += curN + n/curN
                result.add(curN)
                result.add(n/curN)
            }
        }

        if(sum == n) {
            print("$n = ")
            println(result.sorted().joinToString(" + "))
        }
        else {
            println("$n is NOT perfect.")
        }
    }
}
