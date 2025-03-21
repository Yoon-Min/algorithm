import kotlin.math.*

val parent = MutableList(100001) {it}
val edge = mutableListOf<Triple<Int,Int,Int>>()

fun main() = with(System.`in`.bufferedReader()) {
    val (n,m) = readLine().split(" ").map { it.toInt() }
    var result = 0
    var maxWeight = 0
    repeat(m) {
        val (a,b,weight) = readLine().split(" ").map { it.toInt() }
        edge.add(Triple(a,b,weight))
    }
    edge.sortBy { it.third }
    for(node in edge) {
        if(!isSameParent(node.first, node.second)) {
            union(node.first, node.second)
            result += node.third
            maxWeight = node.third
        }
    }
    println(result - maxWeight)
}

fun findParent(n: Int): Int {
    if(parent[n] == n) return n
    parent[n] = findParent(parent[n])
    return parent[n]
}

fun isSameParent(a: Int, b: Int): Boolean {
    val aParent = findParent(a)
    val bParent = findParent(b)
    return aParent == bParent
}

fun union(a: Int, b: Int) {
    val aParent = findParent(a)
    val bParent = findParent(b)
    if(aParent < bParent) parent[bParent] = aParent
    else parent[aParent] = bParent
}
