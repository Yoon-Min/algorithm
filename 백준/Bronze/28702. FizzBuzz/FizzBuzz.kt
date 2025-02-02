import kotlin.math.*
import kotlin.system.exitProcess

fun main() {
    val s = setOf("Fizz", "Buzz", "FizzBuzz")
    var lastNumber = 0
    var numberOrder = 0
    repeat(3) { order ->
        val input = readln()
        if(!s.contains(input)) {
            lastNumber = input.toInt()
            numberOrder = order
        }
    }
    val resultNumber = lastNumber + (3 - numberOrder)
    if(resultNumber%3 == 0 && resultNumber%5 == 0) {
        println("FizzBuzz")
    }
    else if(resultNumber%3 == 0) {
        println("Fizz")
    }
    else if(resultNumber%5 == 0) {
        println("Buzz")
    }
    else {
        println(resultNumber)
    }
}