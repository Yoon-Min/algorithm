fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val w = List(n) { readLine() }.sortedBy { it.length }
    var result = 0

    for (i in w.indices) {
        val cur = w[i]
        val notX = w.drop(i+1).any { cur == it.take(cur.length) }
        if (!notX) result += 1

    }
    println(result)
}