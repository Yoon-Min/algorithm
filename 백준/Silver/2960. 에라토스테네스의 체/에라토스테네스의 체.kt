import kotlin.math.*

fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }
    val isRemoved = MutableList(n+1) { false }
    var count = 0
    for(cur in 2..n) {
        if(isRemoved[cur]) continue
        var isPrime = true
        for(tmp in 2..sqrt(cur.toDouble()).toInt()) {
            if(cur % tmp == 0) {
                isPrime = false
                break
            }
        }
        if(isPrime) {
            for(next in cur..n step cur) {
                if(isRemoved[next]) continue
                isRemoved[next] = true
                count += 1
                if(count == k) {
                    println(next)
                    return
                }
            }
        }
    }
}