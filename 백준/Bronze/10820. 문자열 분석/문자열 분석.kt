import kotlin.math.*

fun main() {
    while(true) {
        val input = readlnOrNull() ?: break
        val a = input.count { it.isLowerCase() }
        val b = input.count { it.isUpperCase() }
        val c = input.count { it.isDigit() }
        val d = input.count { it == ' ' }
        println("$a $b $c $d")
    }
}

