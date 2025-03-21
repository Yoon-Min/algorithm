import kotlin.math.*

val linked = List(100001) { mutableListOf<Int>() }
val seq = MutableList(100001) { 0 }
var nextSeq = 1

fun main() = with(System.`in`.bufferedReader()) {
    val (n,m,r) = readLine().split(" ").map { it.toInt() }
    val q = ArrayDeque<Int>().also { it.add(r) }
    val sb = StringBuilder()
    seq[r] = nextSeq++
    repeat(m) {
        val (u,v) = readLine().split(" ").map { it.toInt() }
        linked[u].add(v)
        linked[v].add(u)
    }
    linked.forEach { it.sortWith(compareBy { e -> -e }) }

    while(q.isNotEmpty()) {
        val cur = q.removeFirst()
        linked[cur].forEach { next ->
            if(seq[next] == 0) {
                seq[next] = nextSeq++
                q.add(next)
            }
        }
    }
    repeat(n) {
        sb.append("${seq[it+1]}\n")
    }
    println(sb)
}
