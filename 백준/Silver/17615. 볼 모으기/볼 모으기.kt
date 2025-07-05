import kotlin.math.*

val n = readln().toInt()
val str = readln().toList()
val redBallsIndex = mutableListOf<Int>()
val blueBallIndex = mutableListOf<Int>()

fun main() = with(System.`in`.bufferedReader()) {
    
    for (i in str.indices) {
        when(str[i]) {
            'R' -> redBallsIndex.add(i)
            'B' -> blueBallIndex.add(i)
        }
    }

    val minRed = min(countMovementToSortBallsToTheLeft('R'), countMovementToSortBallsToTheRight('R'))
    val minBlue = min(countMovementToSortBallsToTheLeft('B'), countMovementToSortBallsToTheRight('B'))
    println(min(minRed,minBlue))
}

fun countMovementToSortBallsToTheLeft(ball: Char): Int {
    var locationToMove = 0
    var counter = 0

    val ballsIndex = if (ball == 'R') redBallsIndex else blueBallIndex

    for (i in ballsIndex) {
        if (i - locationToMove > 0) {
            counter += 1
        }
        locationToMove += 1
    }
//    println("Left $ball $counter")
    return counter
}

fun countMovementToSortBallsToTheRight(ball: Char): Int {
    var locationToMove = str.lastIndex
    var counter = 0

    val ballsIndex = if (ball == 'R') redBallsIndex else blueBallIndex

    for (i in ballsIndex.lastIndex downTo 0) {
        val index = ballsIndex[i]
        if (locationToMove - index > 0) {
            counter += 1
        }
        locationToMove -= 1
    }
//    println("Right $ball $counter")
    return counter
}