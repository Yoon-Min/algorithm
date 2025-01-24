import kotlin.math.*
import kotlin.system.exitProcess

val train = List(100001) { MutableList(21) { 0 } }

fun main() {
    var counter = 0
    val s = mutableSetOf<Int>()
    val (n,m) = readln().split(" ").map { it.toInt() }
    repeat(m) {
        val input = readln().split(" ").map { it.toInt() }
        updateTrain(input)
    }
    for(i in 1..n) {
        val decimalNumber = List(train[i].size) { it -> train[i][it] * 2.0.pow(it-1).toInt() }.sum()
//        println(train[i].joinToString(""))
//        println(decimalNumber)
        if(!s.contains(decimalNumber)) {
            counter += 1
            s.add(decimalNumber)
        }
    }
    println(counter)
}

fun updateTrain(command: List<Int>) {
    val commandNumber = command.first()
    val trainNumber = command[1]
    when(commandNumber) {
        1 -> {
            val seatNumber = command.last()
            if(train[trainNumber][seatNumber] == 0) {
                train[trainNumber][seatNumber] = 1
            }
        }
        2 -> {
            val seatNumber = command.last()
            if(train[trainNumber][seatNumber] == 1) {
                train[trainNumber][seatNumber] = 0
            }
        }
        3 -> { // ->
            var i = 20
            val curTrain = train[trainNumber]
            while(i > 0) {
                if(curTrain[i] == 1) {
                    if(i+1 < 21) curTrain[i+1] = 1
                    curTrain[i] = 0
                }
                i -= 1
            }
        }
        else -> { // <-
            var i = 0
            val curTrain = train[trainNumber]
            while(i < 21) {
                if(curTrain[i] == 1) {
                    if(i-1 > 0) curTrain[i-1] = 1
                    curTrain[i] = 0
                }
                i += 1
            }
        }
    }
}