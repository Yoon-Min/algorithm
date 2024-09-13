import kotlin.math.*

val sawtooth = mutableListOf<MutableList<Int>>(mutableListOf())

val wordStack = mutableListOf<Char>()

fun main() {
    val line = readln()
    var isInSpecialSign = false
    line.forEach {
        when (it) {
            '<' -> {
                printWordStack()
                wordStack.clear()
                isInSpecialSign = true
                print(it)
            }

            '>' -> {
                print(it)
                isInSpecialSign = false
            }

            else -> {
                if (isInSpecialSign) print(it)
                else {
                    wordStack.add(it)
                }
            }
        }
    }
    printWordStack()
}

fun printWordStack() {
    print(wordStack
        .joinToString("")
        .split(" ")
        .joinToString(" ") { it.reversed() }
    )
}