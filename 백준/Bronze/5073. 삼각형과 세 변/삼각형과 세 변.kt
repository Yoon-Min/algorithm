import java.util.*
import kotlin.math.*

val angle = mutableListOf<Int>()

fun main() {
    while(true) {
        val inp = readln().split(" ").map { it.toInt() }.sorted()
        val s = sortedSetOf<Int>() + inp
        if(s.sum() == 0) break
        if(s.size == 1) { println("Equilateral") }
        else if((inp.sum() - inp.last()) <= inp.last()) { println("Invalid") }
        else if(s.size == 2) { println("Isosceles") }
        else { println("Scalene") }
    }
}
