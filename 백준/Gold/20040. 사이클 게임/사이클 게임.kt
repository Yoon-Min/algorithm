val root = MutableList(500000) {it}

fun main() = with(System.`in`.bufferedReader()) {
    val (n,m) = readLine().split(" ").map { it.toInt() }
    repeat(m) { seq ->
        val (a,b) = readLine().split(" ").map { it.toInt() }
        if(findRoot(a) == findRoot(b)) {
            println(seq+1)
            return@main
        }
        union(a,b)
    }
    println(0)
}

fun findRoot(n: Int): Int {
    var nextRoot = n
    while(root[nextRoot] != nextRoot) {
        nextRoot = root[nextRoot]
    }
    return nextRoot
}

fun union(a: Int, b: Int) {
    val aParent = findRoot(a)
    val bParent = findRoot(b)
    if(aParent < bParent) {
        root[bParent] = aParent
    }
    else {
        root[aParent] = bParent
    }
}


