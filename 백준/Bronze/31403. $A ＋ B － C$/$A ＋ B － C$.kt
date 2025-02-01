import kotlin.math.*
import kotlin.system.exitProcess

fun main() {
    val a = readln()
    val b = readln()
    val c = readln()
    println(a.toInt() + b.toInt() - c.toInt())
    println((a+b).toInt() - c.toInt())
}