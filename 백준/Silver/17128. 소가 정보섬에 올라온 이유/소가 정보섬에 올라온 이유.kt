import kotlin.math.*
import kotlin.system.exitProcess

fun main() {
    val (n,totalQ) = readln().split(" ").map { it.toInt() }
    val a = readln().split(" ").map { it.toInt() }.toMutableList()
    val q = readln().split(" ").map { it.toInt() }
    val tmp = MutableList(a.size) {0}
    var result = 0
    repeat(a.size) {
        var i = it
        var mul = 1
        repeat(4) {
            mul *= a[i]
            i = (i+1) % a.size
        }
        tmp[it] = mul
        result += mul
    }

    q.forEach { targetCow ->
        val i = targetCow - 1
        var j = if(i < 3) a.size - (3 - i) else i-3
        var sum = 0
        repeat(4) {
            tmp[j] *= -1
            sum += tmp[j]
            j = (j+1) % a.size
        }
        result += 2*sum
        println(result)
    }
}