import kotlin.math.*

fun main() {
    val vowels = setOf('a', 'e', 'i', 'o', 'u')
    while(true) {
        val inp = readln()
        if(inp == "#") break
        println(inp.count { vowels.contains(it.lowercaseChar())})
    }
}

