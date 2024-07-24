
fun main() {
    val (n, m) = readln().split(' ').map { it.toInt() }
    val arr = MutableList(n) { it+1 }
    repeat(m) {
        val (a, b) = readln().split(' ').map { it.toInt() }
        val tmp = arr[a-1]
        arr[a-1] = arr[b-1]
        arr[b-1] = tmp
    }
    println(arr.joinToString(" "))
}