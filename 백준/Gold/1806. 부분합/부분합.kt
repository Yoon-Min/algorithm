import kotlin.math.*


fun main() = with(System.`in`.bufferedReader()) {
    val (n,s) = readLine().split(" ").map { it.toInt() }
    val per = readLine().split(" ").map { it.toInt() }.toMutableList().also { it.add(0) }
    var sum = per[0]
    var left = 0
    var right = 0
    var length = n+1
    while(right in left..<n) {
        if(sum < s ) {
            sum += per[++right]
        }
        else {
            length = min(length, right-left+1)
            sum -= per[left++]
        }
    }
    if(length == n+1) println(0)
    else println(length)
}