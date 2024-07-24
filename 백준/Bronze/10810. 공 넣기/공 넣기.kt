
fun main() {
    val (n, m) = readln().split(' ').map { it.toInt() }
    val arr = MutableList(n) { 0 }
    repeat(m) {
        val (i, j, k) = readln().split(' ').map { it.toInt() }
        (i..j).forEach {
            arr[it-1] = k
        }
    }
    println(arr.joinToString(" "))
}