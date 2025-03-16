import kotlin.math.*

val linked = List(100001) { mutableListOf<Int>() }
val totalChild = MutableList(100001) {0}

fun main() = with(System.`in`.bufferedReader()) {
    val (n,r,q) = readLine().split(" ").map { it.toInt() }
    val childNodes = List(n+1) { mutableListOf<Int>() }

    repeat(n-1) {
        val (u,v) = readLine().split(" ").map { it.toInt() }
        linked[u].add(v)
        linked[v].add(u)
    }
    setTree(r, -1, childNodes)
    countTotalNode(r, childNodes)
    repeat(q) {
        val nextRoot = readLine().toInt()
        println(totalChild[nextRoot])
    }
}

fun setTree(curNode:Int, parent:Int, childNodes: List<MutableList<Int>>) {
    for(childNode in linked[curNode]) {
        if(childNode != parent) {
            childNodes[curNode].add(childNode)
            setTree(childNode, curNode, childNodes)
        }
    }
}

fun countTotalNode(parent: Int, childNodes: List<MutableList<Int>>) {
    totalChild[parent] = 1
    for(childNode in childNodes[parent]) {
        countTotalNode(childNode, childNodes)
        totalChild[parent] += totalChild[childNode]
    }
}