import java.util.*
import kotlin.math.*

val angle = mutableListOf<Int>()

fun main() {
    repeat(3) { angle.add(readln().toInt()) }
    if(angle.sum() != 180) {
        println("Error")
    }
    else if(angle.toSet().size == 1) {
        println("Equilateral")
    }
    else if(angle.toSet().size == 2) {
        println("Isosceles")
    }
    else {
        println("Scalene")
    }
}
