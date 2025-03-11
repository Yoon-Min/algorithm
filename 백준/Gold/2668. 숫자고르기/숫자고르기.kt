import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val l = mutableListOf<Int>()
    val maxList = mutableListOf<Int>()
    val processed = MutableList(n) { false }
    repeat(n) { l.add(readLine().toInt()-1) }

    for(i in 0 until n) {
        if(processed[i]) continue
        val isVisited = MutableList(n) { false }
        var next = i
        while(!isVisited[l[next]]) {
            next = l[next]
            isVisited[next] = true
        }
        if(next == i) {
            do {
                maxList.add(next)
                processed[next] = true
                next = l[next]
            } while(next != i)
        }
    }
    println(maxList.size)
    maxList.sort()
    maxList.forEach { println(it+1) }
}

