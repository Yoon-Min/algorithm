import kotlin.math.*
import kotlin.system.exitProcess

val seq = mutableListOf<Char>()
val order = MutableList(100) {0}
var counter = 1
var input = readln()

fun main() {
    rec(0, input.lastIndex)
}

fun rec(s: Int, e: Int) {
    if(s > e) return

    var first = input[s]
    var firstIndex = s
    for(i in s..e) {
        if (input[i] < first) {
            firstIndex = i
            first = input[i]
        }
    }

    val priority = mutableListOf<Node>()
    for(i in firstIndex+1..input.lastIndex) {
        if(order[i] == 0) continue
        if(counter > order[i]) {
            priority.add(Node(input[i], i, order[i]))
        }
    }

    if(priority.isEmpty()) {
        seq.add(first)
        order[firstIndex] = counter
    }
    else {
        priority.sortBy { it.order }
        seq.add(priority.last().alphabet)
        for(i in priority.indices.reversed()) {
            val node = priority[i]
            seq[node.order] = seq[node.order-1]
            order[node.index] = node.order + 1
        }
        seq[priority.first().order-1] = first
        order[firstIndex] = priority.first().order
    }
    counter += 1
    println(seq.joinToString(""))

    rec(firstIndex+1, e)
    rec(s, firstIndex-1)
}

data class Node(
    val alphabet: Char,
    val index: Int,
    val order: Int
)




