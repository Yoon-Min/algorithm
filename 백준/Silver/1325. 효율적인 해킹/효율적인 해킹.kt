import java.util.StringTokenizer
import kotlin.collections.ArrayDeque

fun main() = with(System.`in`.bufferedReader()) {
//    val (n,m) = readLine().split(" ").map { it.toInt() }
//    val result = IntArray(n+1)
//    val partner = List(n+1) { mutableSetOf<Int>() }
//
//    repeat(m) {
//        val st = StringTokenizer(readLine())
//        val a = st.nextToken().toInt()
//        val b = st.nextToken().toInt()
//        partner[a].add(b)
//    }
//    val isVisited = BooleanArray(n+1)
//    val q = ArrayDeque<Int>()
//    for(i in 1..n){
//        isVisited.fill(false)
//        isVisited[i] = true
//        q.add(i)
//        while(q.isNotEmpty()) {
//            val cur = q.removeFirst()
//            partner[cur].forEach {
//                if(!isVisited[it]) {
//                    isVisited[it] = true
//                    result[it] += 1
//                    q.add(it)
//                }
//            }
//        }
//    }
//    val max = result.max()
//    val sb = StringBuilder()
//    for(i in 1..n) {
//        if(result[i] == max) sb.append(i).append(" ")
//    }
//    println(sb)
    val (N, M) = readLine().split(" ").map(String::toInt)
    val next = List(N) { mutableListOf<Int>() }.apply {
        for (i in 0 until M) {
            val st = StringTokenizer(readLine())
            this[st.nextToken().toInt() - 1].add(st.nextToken().toInt() - 1)
        }
    }

    val q = ArrayDeque<Int>()
    val answer = IntArray(N)
    val V = BooleanArray(N) { false }
    for (i in 0 until N) {
        V.fill(false)
        V[i] = true
        q.add(i)
        while (q.isNotEmpty()) {
            val now = q.removeFirst()

            for (nxt in next[now]) {
                if (V[nxt].not()) {
                    answer[nxt]++
                    V[nxt] = true
                    q.add(nxt)
                }
            }
        }
    }

    val maxVal = answer.maxOf { it }
    val sb = StringBuilder()
    for (i in 0 until N) {
        if (answer[i] == maxVal) {
            sb.append("${i + 1} ")
        }
    }
    print(sb.dropLast(1))
}
