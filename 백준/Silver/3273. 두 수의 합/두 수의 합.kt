fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val sorted = readLine().split(" ").map { it.toInt() }.sorted()
    val x = readLine().toInt()
    var count = 0
    var l = 0
    var r = sorted.lastIndex

    while(l < r) {
        val leftVal = sorted[l]
        val rightVal = sorted[r]
        val sum = leftVal + rightVal
        if(sum > x) r -= 1
        else if(sum < x) l += 1
        else {
            l += 1
            r -= 1
            count += 1
        }
    }
    println(count)
}

