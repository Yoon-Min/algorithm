import kotlin.math.*

fun main() {
    val n = readln().toInt()
    val report = readln().split(" ").map { it.toInt() }
    val dq = ArrayDeque<Pair<Int,Int>>().also { it.addAll(report.mapIndexed { i, t -> Pair(i+1, t) }) }
    val result = MutableList(n) { 0 }

    while(dq.isNotEmpty()) {
        val cur = dq.removeFirst()
        val curN = cur.first
        val tallerCount = cur.second
        var count = 0
        var i = 0
        while(i < n) {
            if(count == tallerCount) {
                while(result[i] > 0) i += 1
                break
            }
            if(result[i] == 0) {
                count += 1
            }
            i += 1
        }
        result[i] = curN
    }
    println(result.joinToString(" "))
}


