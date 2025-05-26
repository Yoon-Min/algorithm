fun main() = with(System.`in`.bufferedReader()) {
    var cnt = 0
    var change = 1000 - readLine().toInt()
    val changeUnit = listOf(500,100,50,10,5,1)

    for(unit in changeUnit) {
        if(change <= 0) break
        cnt += change / unit
        change %= unit
    }

    println(cnt)
}