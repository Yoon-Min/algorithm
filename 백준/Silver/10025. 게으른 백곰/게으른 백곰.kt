import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val ice = MutableList(1000001) {0}
    val (n,k) = readLine().split(" ").map { it.toInt() }
    var result = 0

    repeat(n) {
        val (g,x) = readLine().split(" ").map { it.toInt() }
        ice[x] = g
    }

    var left = 0
    var right = 2*k

    if(k >= 500000) {
        println(ice.sum())
        return
    }

    var sum = (0..right).sumOf { i -> ice[i] }

    while(true) {
        result = max(result, sum)
        if(right+1 > 1000000) break
        sum -= ice[left++]
        sum += ice[++right]
    }

    println(result)

}