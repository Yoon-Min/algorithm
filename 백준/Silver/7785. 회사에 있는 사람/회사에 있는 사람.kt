import java.util.*
import kotlin.math.*

fun main() {
    val n = readln().toInt()
    val inCompany = mutableSetOf<String>()
    repeat(n) {
        val (name, state) = readln().split(" ")
        when(state) {
            "enter" -> inCompany.add(name)
            else -> inCompany.remove(name)
        }
    }
    inCompany.sortedDescending().forEach { println(it) }
}