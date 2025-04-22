fun main() = with(System.`in`.bufferedReader()) {
    val (n,s) = readLine().split(" ").map { it.toInt() }
    val cowLength = mutableListOf<Int>()
        .also { l -> repeat(n) { i -> l.add(readLine().toInt()) } }
        .sorted()

    var left = 0
    var right = cowLength.lastIndex
    var count = 0

    while(left < right) {
        val sum = cowLength[left] + cowLength[right]
        if(sum <= s) {
            count += (right - left)
            left += 1
            right = cowLength.lastIndex
        }
        else {
            right -= 1
        }
    }
    println(count)
}