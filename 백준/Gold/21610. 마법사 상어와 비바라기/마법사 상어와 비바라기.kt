import kotlin.math.*

val dVector = listOf(
    Pair(0,0),
    Pair(0,-1),
    Pair(-1,-1),
    Pair(-1,0),
    Pair(-1,1),
    Pair(0,1),
    Pair(1,1),
    Pair(1,0),
    Pair(1,-1)
)

val diagonalVector = listOf(
    Pair(-1,-1),
    Pair(-1,1),
    Pair(1,1),
    Pair(1,-1)
)

val nm = readln().split(" ").map { it.toInt() }
val n = nm.first()
val m = nm.last()
val water = List(n) { readln().split(" ").map { it.toInt() }.toMutableList() }
val cloud = List(n) { MutableList(n) { false } }
val increasedPos = mutableListOf<Pair<Int,Int>>()
val removedPos = mutableSetOf<Pair<Int,Int>>()

fun main() = with(System.`in`.bufferedReader()) {

    cloud[n-1][0] = true
    cloud[n-1][1] = true
    cloud[n-2][0] = true
    cloud[n-2][1] = true

    repeat(m) {
        val (d, s) = readLine().split(" ").map { it.toInt() }
        moveAllCloud(d,s)
        raining()
        removeAllCloud()
        increaseHeightAtDiagonal()
        createCloud()
    }

    var sum = 0
    for(i in 0 until n) {
        for(j in 0 until n) {
            sum += water[i][j]
        }
    }

    println(sum)
}

fun moveAllCloud(dir: Int, dist: Int) {
    val nextAllPos = mutableListOf<Pair<Int,Int>>()
    for(i in 0 until n) {
        for(j in 0 until n) {
            if(cloud[i][j]) {
                val next = getNextPosition(dir, dist, Pair(i,j))
                nextAllPos.add(next)
                cloud[i][j] = false
            }
        }
    }
    nextAllPos.forEach { p ->
        cloud[p.first][p.second] = true
    }
}

fun raining() {
    for(i in 0 until n) {
        for(j in 0 until n) {
            if(cloud[i][j]) {
                water[i][j] += 1
                increasedPos.add(Pair(i,j))
            }
        }
    }
}

fun removeAllCloud() {
    for(i in 0 until n) {
        for(j in 0 until n) {
            if(cloud[i][j]) {
                cloud[i][j] = false
                removedPos.add(Pair(i,j))
            }
        }
    }
}

fun increaseHeightAtDiagonal() {
    increasedPos.forEach { p ->
        for(v in diagonalVector) {
            val nextCol = p.first + v.first
            val nextRow = p.second + v.second
            if(nextCol !in 0 until n || nextRow !in 0 until n) continue
            if(water[nextCol][nextRow] > 0) {
                water[p.first][p.second] += 1
            }
        }
    }
    increasedPos.clear()
}

fun createCloud() {
    for(i in 0 until n) {
        for(j in 0 until n) {
            if(water[i][j] > 1 && !removedPos.contains(Pair(i,j))) {
                cloud[i][j] = true
                water[i][j] -= 2
            }
        }
    }
    removedPos.clear()
}

fun getNextPosition(dir: Int, dist: Int, pos: Pair<Int,Int>): Pair<Int,Int> {
    val v = dVector[dir]
    var nextCol = pos.first
    var nextRow = pos.second

    repeat(dist) {
        nextCol += v.first
        nextRow += v.second

        if(nextCol == -1) nextCol = n-1
        else if(nextCol == n) nextCol = 0

        if(nextRow == -1) nextRow = n-1
        else if(nextRow == n) nextRow = 0
    }

    return Pair(nextCol, nextRow)
}

fun printCloud() {
    cloud.forEach {
        println(it.map { if(it) 1 else 0 }.joinToString(" "))
    }
    println()
}



