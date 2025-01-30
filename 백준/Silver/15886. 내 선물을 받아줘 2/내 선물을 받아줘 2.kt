import kotlin.math.*
import kotlin.system.exitProcess

val directionVector = listOf(Pair(0,1), Pair(0,-1))

fun main() {
    val n = readln().toInt()
    val input = readln()
    val isVisited = MutableList(n) { false }
    for(i in input.indices) {
        val curChar = input[i]
        var j = i
        val vector = if(curChar == 'E') directionVector[0] else directionVector[1]
        while(j in input.indices && input[j] == curChar) {
            j += vector.second
        }
        val prevIndex = j +(-1*vector.second)
        if(j in input.indices) {
            if(!isVisited[prevIndex] && !isVisited[j]) isVisited[prevIndex] = true
        }
        else {
            isVisited[prevIndex] = true
        }
    }
    println(isVisited.count { it })
}