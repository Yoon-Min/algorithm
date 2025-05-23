import java.util.*

val root = MutableList(1001) { it }

fun main() = with(System.`in`.bufferedReader()) {
    repeat(readLine().toInt()) {
        (0..1000).forEach { root[it] = it }
        val (n,m) = readLine().split(" ").map { it.toInt() }
        val mList = mutableListOf<Pair<Int,Int>>()
        var cnt = 0

        repeat(m) {
            val (a,b) = readLine().split(" ").map { it.toInt() }
            mList.add(Pair(a,b))
        }

        while(mList.isNotEmpty()) {
            val cur = mList.removeLast()
            if(!isSameParent(cur.first,cur.second)) {
                cnt += 1
                unionParent(cur.first,cur.second)
            }
        }

        println(cnt)
    }
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