import kotlin.math.*
import kotlin.system.exitProcess

const val MAX_RANGE = 100000000

fun main() = with(System.`in`.bufferedReader()) {
    val (n,k) = readLine().split(" ").map { it.toInt() }
    val waterfront = readLine().split(" ").map { it.toInt() }.sorted()
    var pendingK = k
    var sum = 0L
    val visited = mutableSetOf<Int>()
    val q = ArrayDeque<Pair<Int,Int>>().also {
        waterfront.forEach {
            pos -> it.add(Pair(pos,1))
            visited.add(pos)
        }
    }
    while(true) {
        val cur = q.removeFirst()
        val left = cur.first-1
        val right = cur.first+1
        listOf(left,right).forEach { next ->
            if(!visited.contains(next)) {
                visited.add(next)
                sum += cur.second
                pendingK -= 1
                if(pendingK == 0) {
                    println(sum)
                    exitProcess(0)
                }
                q.add(Pair(next,cur.second+1))
            }
        }
    }
}

