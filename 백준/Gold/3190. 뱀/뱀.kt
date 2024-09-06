import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.*

fun main() {
    val n = readln().toInt()
    val k = readln().toInt()
    val map = MutableList(n+1) { MutableList(n+1) {0} }
    val direction = listOf(listOf(-1,0), listOf(0,1), listOf(1,0), listOf(0,-1)) // [ North, East, South, West ]
    val xDirection = MutableList(10001) { "" }

    repeat(k) {
        val (i, j) = readln().split(" ").map { it.toInt() }
        map[i][j] = 1
    }
    val l = readln().toInt()
    repeat(l) {
        val (x, c) = readln().split(" ")
        xDirection[x.toInt()] = c
    }

    /*
        blank = 0
        apple = 1
        snake = 2
     */
    map[1][1] = 2
    val snake = ArrayDeque<Pair<Int,Int>>().also { it.addFirst(Pair(1,1)) } // Order : Head -> Body -> Tail
    var curDirection = 1
    var totalSec = 0
    var i = 1
    var j = 1
    while(true) {
        totalSec += 1
        val nextDirection = direction[curDirection]
        val nextI = i + nextDirection[0]
        val nextJ = j + nextDirection[1]
        if(nextI in (1.. n) && nextJ in (1.. n)) {
            if(map[nextI][nextJ] == 2) {
                break
            }
            else if(map[nextI][nextJ] == 0) {
                val tail = snake.removeLast()
                map[tail.first][tail.second] = 0
            }
            else {
                map[nextI][nextJ] = 0
            }
            i = nextI
            j = nextJ
            snake.addFirst(Pair(i,j))
            map[nextI][nextJ] = 2
        }
        else {
            break
        }
        if(xDirection[totalSec].isNotBlank()) {
            curDirection = when(xDirection[totalSec]) {
                "D" -> getNextDirectionByD(curDirection)
                else -> getNextDirectionByL(curDirection)
            }
        }

//        map.slice(1..n).forEach {
//            println(it.slice(1..n).joinToString(""))
//        }
//        println(totalSec)
//        println()
    }
    println(totalSec)
}

fun getNextDirectionByD(curDirection: Int): Int {
    return when(curDirection) {
        0 -> 1
        1 -> 2
        2 -> 3
        else -> 0
    }
}

fun getNextDirectionByL(curDirection: Int): Int {
    return when(curDirection) {
        0 -> 3
        1 -> 0
        2 -> 1
        else -> 2
    }
}

