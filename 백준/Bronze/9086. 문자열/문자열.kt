
fun main() {
    val n = readln().toInt()
    repeat(n) {
        val str = readln()
        println("${str.first()}${str.last()}")
    }
}