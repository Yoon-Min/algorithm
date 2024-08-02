import java.util.*
import kotlin.math.*

val angle = mutableListOf<Int>()

fun main() {
    val tri = readln().split(" ").map { it.toInt() }.sorted()
    var a = tri[0] + tri[1]
    var b = tri.last()
    while(a <= b) { b-- }
    println(a+b)
}
