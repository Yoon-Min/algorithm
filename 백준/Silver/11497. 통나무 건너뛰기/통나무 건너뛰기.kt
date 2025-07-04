import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    repeat(readLine().toInt()) {
        val n = readLine().toInt()
        val arr = readLine().split(" ").map { it.toInt() }.sorted().toMutableList()
        val q = ArrayDeque<Int>().also { it.add(arr.removeLast()) }

        var left = true
        while(arr.isNotEmpty()) {
            if (left) {
                q.addFirst(arr.removeLast())
                left = false
            }
            else {
                q.addLast(arr.removeLast())
                left = true
            }
        }
        var maxDiff = 0
        for(i in 1..q.lastIndex) {
            val a = q[i-1]
            val b = q[i]
            maxDiff = max(maxDiff, abs(b-a))
        }

        println(maxDiff)
    }

}