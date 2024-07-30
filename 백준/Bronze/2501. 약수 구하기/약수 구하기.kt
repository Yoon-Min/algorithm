import java.util.*
import kotlin.math.*

fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }
    var counter = 0
    var result = 0
    for(curN in 1..n) {
        if(n % curN == 0) {
            counter++
            if(counter == k) {
                result = curN
                break
            }
        }
    }
    println(result)
}
