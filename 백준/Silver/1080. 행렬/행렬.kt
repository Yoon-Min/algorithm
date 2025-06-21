import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n,m) = readLine().split(" ").map { it.toInt() }
    val aMatrix = List(n) { mutableListOf<Int>() }
    val bMatrix = List(n) { mutableListOf<Int>() }

    repeat(n) {
        aMatrix[it].addAll(readLine().map { c -> c.code - 48 })
    }
    repeat(n) {
        bMatrix[it].addAll(readLine().map { c -> c.code - 48 })
    }

    var cnt = 0

    for(i in 0 until n-2) {
        for(j in 0 until m-2) {
            if(aMatrix[i][j] != bMatrix[i][j]) {
                for(k in i until i+3) {
                    for(l in j until j+3) {
                        aMatrix[k][l] = 1 - aMatrix[k][l]
                    }
                }
                cnt += 1
            }
        }
    }

    for(i in 0 until n) {
        for(j in 0 until m) {
            if(aMatrix[i][j] != bMatrix[i][j]) {
                println(-1)
                return
            }
        }
    }

    println(cnt)
}