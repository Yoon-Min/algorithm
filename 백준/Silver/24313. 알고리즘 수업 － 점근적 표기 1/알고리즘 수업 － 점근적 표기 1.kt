import java.util.*
import kotlin.math.*

fun main() {
    /*
    O(g(n)) = {f(n) | 모든 n ≥ n0에 대하여 f(n) ≤ c × g(n)인 양의 상수 c와 n0가 존재한다}
    f(n) = a1n + a0
     */
    val (a1, a0) = readln().split(" ").map { it.toInt() }
    val c = readln().toInt()
    val n0 = readln().toInt()

    val n = n0
    val gn = n
    val fn = a1 * n + a0

    /*
     a1 * n + a0 <= cn
     n(a1 - c) <= -a0
     n <= -a0 / (a1 - c)
     left = n, right = a1 - c
     */
    var result = 0
    val right = a1 - c
    if (right == 0) {
        if(a0 <= 0) result = 1
    }
    else if(right < 0) {
        if(n >= (-1*a0 / right)) result = 1
    }
    println(result)
}