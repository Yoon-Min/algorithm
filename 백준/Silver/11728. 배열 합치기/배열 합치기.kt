import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n,m) = readLine().split(" ").map { it.toInt() }
    val a = readLine().split(" ").map { it.toInt() }
    val b = readLine().split(" ").map { it.toInt() }

    var aPointer = 0
    var bPointer = 0

    val sb = StringBuilder()

    while(aPointer < n && bPointer < m) {
        if(a[aPointer] > b[bPointer]) {
            sb.append("${b[bPointer]} ")
            bPointer += 1
        }
        else {
            sb.append("${a[aPointer]} ")
            aPointer += 1
        }
    }

    (aPointer until n).forEach { i -> sb.append("${a[i]} ")}
    (bPointer until m).forEach { i -> sb.append("${b[i]} ")}

    println(sb)
}