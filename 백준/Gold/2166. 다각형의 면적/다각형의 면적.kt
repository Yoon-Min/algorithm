import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val p = mutableListOf<Pair<Double,Double>>()
    var sum = 0.0
    repeat(n) {
        val (a,b) = readLine().split(" ").map { it.toDouble() }
        p.add(Pair(a,b))
    }
    p.add(p[0])
    for(i in 0 until n) {
        sum += p[i].first * p[i+1].second - p[i+1].first * p[i].second
    }
    println(String.format("%.1f", abs(sum)/2))
}