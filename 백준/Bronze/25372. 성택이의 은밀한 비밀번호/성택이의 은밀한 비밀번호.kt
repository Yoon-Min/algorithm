import kotlin.math.*

fun main() {
    repeat(readln().toInt()) {
        val inputLength = readln().length
        if(inputLength in 6..9) {
            println("yes")
        }
        else {
            println("no")
        }
    }
}

