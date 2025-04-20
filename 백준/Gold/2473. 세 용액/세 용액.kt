import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {

    val n = readLine().toInt()
    val l = readLine().split(" ").map { it.toLong() }.sorted()

    var resultA = 0L
    var resultB = 0L
    var resultC = 0L
    var minSum = Long.MAX_VALUE

    for(i in 0 until n) {
        val target = l[i]

        var s = if(i == 0) 1 else 0
        var e = if(i == n-1) n-2 else n-1

        var resultS = s
        var resultE = e
        var resultSum = abs(l[s] + l[e] + target)

        while(s < e) {
            val sum = l[s] + l[e] + target

            if(abs(sum) < resultSum) {
                resultS = s
                resultE = e
                resultSum = abs(sum)
                if(resultSum == 0L) {
                    break
                }
            }

            if(sum < 0) {
                s += if(s+1 == i) 2 else 1
            }
            else {
                e -= if(e-1 == i) 2 else 1
            }
        }

        if(resultSum < minSum) {
            resultA = l[resultS]
            resultB = l[resultE]
            resultC = target
            minSum = resultSum
        }
    }
    println(listOf(resultA,resultB,resultC).sorted().joinToString(" "))
}
