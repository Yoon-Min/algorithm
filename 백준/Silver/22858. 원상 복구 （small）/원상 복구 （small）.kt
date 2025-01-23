import kotlin.math.*
import kotlin.system.exitProcess

fun main() {
    val (n,k) = readln().split(" ").map { it.toInt() }
    val s = readln().split(" ").map { it.toInt() }.toMutableList()
    val d = readln().split(" ").map { it.toInt() }
    val pureCardSet = MutableList(n) {0}

    repeat(k) {
        for(i in 0..< n) {
            val prevPosition = d[i]-1
            pureCardSet[prevPosition] = s[i]
        }
        for(i in 0..< n) {
            s[i] = pureCardSet[i]
        }
    }
    println(pureCardSet.joinToString(" "))
}