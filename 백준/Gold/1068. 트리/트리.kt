import kotlin.math.*

var leafCountFromDeletionNode = 0

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val parent = readLine().split(" ").map { it.toInt() }
    val child = List(n) { mutableListOf<Int>() }
    val removalNode = readLine().toInt()
    for(i in parent.indices) {
        if(parent[i] == -1) continue
        child[parent[i]].add(i)
    }
    setTotalLeafCount(removalNode, child)
    var totalLeafNode = child.count { it.isEmpty() } - leafCountFromDeletionNode
    if(parent[removalNode] != - 1 && child[parent[removalNode]].size == 1) {
        totalLeafNode += 1
    }
    println(totalLeafNode)
}

fun setTotalLeafCount(root: Int, child: List<MutableList<Int>>) {
    if(child[root].isEmpty()) {
        leafCountFromDeletionNode += 1
        return
    }
    for(n in child[root]) {
        setTotalLeafCount(n, child)
    }
}