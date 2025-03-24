import kotlin.system.exitProcess

val n = readln().toInt()
val productsOf = List(n) { readln().split(" ").map { it.toInt() } }
val tmpStack = mutableListOf<Int>()
val isNoneRoute = List(n) { i -> MutableList(productsOf[i].size) { false } }

fun main() = with(System.`in`.bufferedReader()) {
    dfs(0)
    println(-1)
}

fun dfs(nextDay: Int) {
    if(tmpStack.size == n) {
        tmpStack.forEach { println(it) }
        exitProcess(0)
    }
    for(i in 1..productsOf[nextDay].lastIndex) {
        val productNum = productsOf[nextDay][i]
        if(!isNoneRoute[nextDay][i] && (tmpStack.isEmpty() || tmpStack.last() != productNum)) {
            tmpStack.add(productNum)
            dfs(nextDay+1)
            isNoneRoute[nextDay][i] = true
            tmpStack.removeLast()
        }
    }
}