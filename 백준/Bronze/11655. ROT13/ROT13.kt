import kotlin.math.*

fun main() {
    println(
        readln().map { c ->
            when (c.code) {
                in 65..90 -> {
                    if(c.code+13 > 90) { ((c.code+13)%91 + 65).toChar() }
                    else (c.code+13).toChar()
                }
                in 97..122 -> {
                    if(c.code+13 > 122) { ((c.code+13) % 123 + 97).toChar() }
                    else (c.code+13).toChar()
                }
                else -> c
            }
        }.joinToString("")
    )
}