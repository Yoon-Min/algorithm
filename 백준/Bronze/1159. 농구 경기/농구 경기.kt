import kotlin.math.*

fun main() {
    val hm = hashMapOf<Char,Int>()
    repeat(readln().toInt()) {
        val name = readln()
        hm[name.first()] = hm.getOrDefault(name.first(), 0) + 1
    }
    val entry = hm.filter { it.value > 4 }.keys.sorted()
    if(entry.isEmpty()) println("PREDAJA")
    else println(entry.joinToString(""))
}

