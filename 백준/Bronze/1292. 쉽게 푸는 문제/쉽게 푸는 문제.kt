import kotlin.math.*

fun main() {
    val stack = mutableListOf(0)
    val (a, b) = readln().split(" ").map { it.toInt() }
    var counter = 1
    var curNum = 1
    while(counter <= 1000) {
        for(n in 1..curNum) {
            stack.add(stack.last() + curNum)
        }
        curNum += 1
        counter += 1
    }
    println(stack[b] - stack[a-1])
}
