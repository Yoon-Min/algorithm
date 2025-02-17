import java.util.StringTokenizer
import kotlin.collections.ArrayDeque

fun main() = with(System.`in`.bufferedReader()) {
    val (n,m) = readLine().split(" ").map { it.toInt() }
    val result = IntArray(n+1)
    val partner = List(n+1) { mutableListOf<Int>() }

    for(i in 0 until m) {
        val st = StringTokenizer(readLine())
        partner[st.nextToken().toInt()].add(st.nextToken().toInt())
    }

    val isVisited = BooleanArray(n+1)
    val q = ArrayDeque<Int>()
    for(i in 1..n){
        isVisited.fill(false)
        isVisited[i] = true
        q.add(i)
        while(q.isNotEmpty()) {
            val cur = q.removeFirst()
            for(it in partner[cur]){
                if(!isVisited[it]) {
                    isVisited[it] = true
                    result[it] += 1
                    q.add(it)
                }
            }
        }
    }
    val max = result.max()
    val sb = StringBuilder()
    for(i in 1..n) {
        if(result[i] == max) sb.append("$i ")
    }
    println(sb)
}
