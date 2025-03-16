import kotlin.math.*

val parent = MutableList(10001) {it}

fun main() = with(System.`in`.bufferedReader()) {
    val (v,e) = readLine().split(" ").map { it.toInt() }
    val edge = mutableListOf<Triple<Int,Int,Int>>()
    var dist = 0L
    repeat(e) {
        val (a,b,w) = readLine().split(" ").map { it.toInt() }
        edge.add(Triple(a,b,w))
    }
    edge.sortWith(compareBy { it.third })
    for(i in edge.indices) {
        val cur = edge[i]
        val a = cur.first
        val b = cur.second
        if(!checkSameParent(a,b)) {
            dist += cur.third.toLong()
            unionParent(a,b)
        }
    }
    println(dist)
}

fun unionParent(a: Int, b: Int) {
    val aParent = getParent(a)
    val bParent = getParent(b)
    if(aParent < bParent) parent[bParent] = aParent
    else parent[aParent] = bParent
}

fun getParent(n: Int): Int {
    if(parent[n] == n) return n
    parent[n] = getParent(parent[n])
    return parent[n]
}

fun checkSameParent(a: Int, b: Int): Boolean {
    return getParent(a) == getParent(b)
}
