import kotlin.math.*

var joinCount = 0

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val ps = MutableList(n+1) {0}
    var answer = 0

    repeat(n) {
        ps[it+1] = readLine().toInt() + ps[it]
    }

    for(i in 1..n) {
        var left = i
        var right = n
        while(left <= right) {
            val mid = (left+right) / 2
            val rightCirculationDist = ps[mid] - ps[i-1]
            val leftCirculationDist =  ps.last() - rightCirculationDist

            answer = max(answer, min(rightCirculationDist, leftCirculationDist))

            if(rightCirculationDist < leftCirculationDist) {
                left = mid + 1
            }
            else {
                right = mid - 1
            }
        }
    }
    println(answer)
}