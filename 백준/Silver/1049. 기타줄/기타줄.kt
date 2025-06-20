import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n,m) = readLine().split(" ").map { it.toInt() }
    val packagePrices = mutableListOf<Int>()
    val singlePrices = mutableListOf<Int>()

    repeat(m) {
        val (p,s) = readLine().split(" ").map { it.toInt() }
        packagePrices.add(p)
        singlePrices.add(s)
    }
    singlePrices.sort()
    packagePrices.sort()

    if(n < 6) {
        println(min(singlePrices.first()*n, packagePrices.first()))
    }
    else {
        var remainedN = n
        var totalPrice = 0
        val minPrice = min(packagePrices.first(), singlePrices.first() * 6)
        totalPrice += minPrice * floor(remainedN.toDouble()/6).toInt()
        remainedN %= 6
        totalPrice += min(remainedN * singlePrices.first(), minPrice)
        println(totalPrice)
    }
}