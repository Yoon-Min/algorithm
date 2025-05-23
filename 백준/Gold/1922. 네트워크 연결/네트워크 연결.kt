import java.util.*

val root = MutableList(1001) { it }

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val m = readLine().toInt()
    val pq = PriorityQueue<Triple<Int,Int,Int>>(compareBy { it.third })
    var cost = 0

    repeat(m) {
        val (a,b,c) = readLine().split(" ").map { it.toInt() }
        pq.offer(Triple(a,b,c))
    }

    while(pq.isNotEmpty()) {
        val cur = pq.poll()

        if(!isSameParent(cur.first,cur.second)) {
            cost += cur.third
            unionParent(cur.first,cur.second)
        }
    }

    println(cost)
}

fun findParent(node: Int): Int {
    var parent = node
    while(root[parent] != parent) {
        parent = root[parent]
    }
    root[node] = parent
    return parent
}

fun unionParent(a: Int, b: Int) {
    val aParent = findParent(a)
    val bParent = findParent(b)
    if(aParent < bParent) root[bParent] = aParent
    else root[aParent] = bParent
}

fun isSameParent(a: Int, b: Int): Boolean {
    val aParent = findParent(a)
    val bParent = findParent(b)
    return aParent == bParent
}