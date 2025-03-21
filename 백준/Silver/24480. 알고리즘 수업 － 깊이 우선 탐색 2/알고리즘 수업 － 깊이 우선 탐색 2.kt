import kotlin.math.*

val linked = List(100001) { mutableListOf<Int>() }
val seq = MutableList(100001) { 0 }
var nextSeq = 1

fun main() = with(System.`in`.bufferedReader()) {
    val (n,m,r) = readLine().split(" ").map { it.toInt() }
    val sb = StringBuilder()
    seq[r] = nextSeq++
    repeat(m) {
        val (u,v) = readLine().split(" ").map { it.toInt() }
        linked[u].add(v)
        linked[v].add(u)
    }
    linked.forEach { it.sortWith(compareBy { e -> -e }) }
    dfs(r)
    repeat(n) {
        sb.append("${seq[it+1]}\n")
    }
    println(sb)
}

fun dfs(s: Int) {
    linked[s].forEach { n ->
        if(seq[n] == 0) {
            seq[n] = nextSeq++
            dfs(n)
        }
    }
}
