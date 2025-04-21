import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {

    val (n,m) = readLine().split(" ").map { it.toInt() }
    val l = readLine().split(" ").map { it.toInt() }

    var result = 0
    var left = 0
    var right = 0
    var sum = l.first()

    while(true) {
        if(sum >= m) {
            if(sum == m) result += 1
            sum -= l[left++]
        }
        else if(right == n-1) {
            break
        }
        else {
            sum += l[++right]
        }
    }
    println(result)
}
