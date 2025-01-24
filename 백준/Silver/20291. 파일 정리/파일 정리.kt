import kotlin.math.*
import kotlin.system.exitProcess

fun main() {
    val hm = hashMapOf<String, Int>()
    repeat(readln().toInt()) {
        val key = readln().split('.').last()
        hm[key] = hm.getOrDefault(key, 0) + 1
    }
    hm.toList().sortedBy { it.first }.forEach { p -> println("${p.first} ${p.second}") }
}