
fun main() {
    val (n, m) = readln().split(' ').map { it.toInt() }
    val arr = MutableList(n) { it+1 }
    repeat(m) {
        val (i, j) = readln().split(' ').map { it.toInt() - 1 }
        val mid = (j+i)/2
        var otherPointer = j
        (i.. mid).forEach {
            val tmp = arr[it]
            arr[it] = arr[otherPointer]
            arr[otherPointer--] = tmp
        }
    }
    println(arr.joinToString(" "))
}