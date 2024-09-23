import kotlin.math.*

fun main() {
    val self = readln()
    val request = readln()
    if(self.count { it == 'a' } >= request.count { it == 'a' }) println("go")
    else println("no")
}