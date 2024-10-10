import kotlin.math.*

fun main() {
    val n = readln().toInt()
    val garbageStack = mutableListOf<Int>()
    val dq = ArrayDeque<Int>().also { it.addAll((1..n))}
    while(dq.size > 1) {
        garbageStack.add(dq.removeFirst())
        if(dq.size == 1) break
        dq.addLast(dq.removeFirst())
    }
    garbageStack.add(dq.removeLast())
    println(garbageStack.joinToString(" "))
}
