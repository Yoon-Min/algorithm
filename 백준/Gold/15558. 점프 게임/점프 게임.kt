import kotlin.system.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n,k) = readLine().split(" ").map { it.toInt() }
    val line = List(2) { readLine().map { it.code - 48 }.toMutableList() }
    val q = ArrayDeque<Triple<Int,Int,Int>>().also { it.add(Triple(0,0,0)) }
    val dVector = listOf(1, -1)
    var s = 0
    line[0][0] = 0

    while(q.isNotEmpty()) {
        val cur = q.removeFirst()
        val curSeq = cur.first
        val linePosition = cur.second
        val depth = cur.third

//        println("root - $cur")

        if(curSeq >= n) {
            println(1)
            exitProcess(0)
        }
        if(s < depth) {
            s += 1
        }
//        println(s)
        if(curSeq < s) {
            continue
        }
        dVector.forEach { v ->
            val next = curSeq + v
            if(next in s until n && line[linePosition][next] == 1) {
                line[linePosition][next] = 0
//                println("child - ${Triple(next, linePosition, depth+1)}")
                q.add(Triple(next, linePosition, depth+1))
            }
            else if(next >= n) {
//                println("child - ${Triple(next, linePosition, depth+1)}")
                q.add(Triple(next, linePosition, depth+1))
            }
        }
        val next = curSeq + k
        val nextLinePosition = linePosition xor 1
        if(next in s until n && line[nextLinePosition][next] == 1) {
            line[nextLinePosition][next] = 0
//            println("child - ${Triple(next, nextLinePosition, depth+1)}")
            q.add(Triple(next, nextLinePosition, depth+1))
        }
        else if(next >= n) {
//            println("child - ${Triple(next, nextLinePosition, depth+1)}")
            q.add(Triple(next, nextLinePosition, depth+1))
        }
//        println("end")
    }
    println(0)
}
