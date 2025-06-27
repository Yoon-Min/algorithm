import kotlin.math.*

val dVector = listOf(
    Pair(-1,0),
    Pair(-1,1),
    Pair(0,1),
    Pair(1,1),
    Pair(1,0),
    Pair(1,-1),
    Pair(0,-1),
    Pair(-1,-1),
)

val nmk = readln().split(" ").map { it.toInt() }
val n = nmk[0]
val m = nmk[1]
val k = nmk[2]

// 0 : 파이어볼 갯수 1 : 질량 총 합 2 : 속력 총 합 3 : 짝수는 -1 홀수는 -2 믹스면 -3
val ball = List(n) { List(n) { MutableList(4) {0} } }
val tmpMap = List(n) { List(n) { MutableList(4) {0} } }

fun main() = with(System.`in`.bufferedReader()) {
    repeat(m) {
        val fireBall = readLine().split(" ").map { it.toInt() }
        val col = fireBall[0]-1
        val row = fireBall[1]-1
        ball[col][row][0] = 1

        repeat(3) { i ->
            ball[col][row][i+1] = fireBall[i+2]
        }
    }

    repeat(k) {
        moveFireBalls()
        updateFireBalls()
        initTmpMap()
    }

    var sum = 0
    for(i in 0 until n) {
        for(j in 0 until n) {
            if(ball[i][j][0] > 0) {
                sum += ball[i][j][1] * ball[i][j][0]
            }
        }
    }

    println(sum)
}

fun initTmpMap() {
    for(i in 0 until n) {
        for(j in 0 until n) {
            for(k in 0..3) {
                tmpMap[i][j][k] = 0
            }
        }
    }
}

fun moveFireBalls() {
    for(i in 0 until n) {
        for(j in 0 until n) {
            if(ball[i][j][0] > 0) {
                if(ball[i][j][0] == 1) {
                    val next = getNextPosition(ball[i][j][3], ball[i][j][2], Pair(i,j))
                    tmpMap[next.first][next.second][0] += 1

                    if(tmpMap[next.first][next.second][0] == 1) {
                        tmpMap[next.first][next.second][3] = ball[i][j].last()
                    }
                    else {
                        val directionGroup = getDirectionGroup(ball[i][j].last(), tmpMap[next.first][next.second][3])
                        tmpMap[next.first][next.second][3] = directionGroup
                    }

                    repeat(2) { tmpMap[next.first][next.second][it+1] += ball[i][j][it+1] }
                }
                else if(ball[i][j][0] == 4) {
                    val nextVector = if(ball[i][j].last() != -3) listOf(0,2,4,6) else listOf(1,3,5,7)

                    for(v in nextVector) {
                        val next = getNextPosition(v, ball[i][j][2], Pair(i,j))
                        tmpMap[next.first][next.second][0] += 1

                        if(tmpMap[next.first][next.second][0] == 1) {
                            tmpMap[next.first][next.second][3] = v
                        }
                        else {
                            val directionGroup = getDirectionGroup(v, tmpMap[next.first][next.second][3])
                            tmpMap[next.first][next.second][3] = directionGroup
                        }

                        repeat(2) { tmpMap[next.first][next.second][it+1] += ball[i][j][it+1] }
                    }
                }
            }
        }
    }

    for(i in 0 until n) {
        for(j in 0 until n) {
            repeat(4) { ball[i][j][it] = tmpMap[i][j][it] }
        }
    }
}

fun updateFireBalls() {
    for(i in 0 until n) {
        for(j in 0 until n) {
            if(ball[i][j][0] > 1) {
                ball[i][j][1] /= 5
                ball[i][j][2] /= ball[i][j][0]
                ball[i][j][0] = 4
            }
            if(ball[i][j][0] > 0 && ball[i][j][1] == 0) {
                repeat(4) { ball[i][j][it] = 0 }
            }
        }
    }
}

fun getNextPosition(dir: Int, dist: Int, cur: Pair<Int,Int>): Pair<Int,Int> {
//    var nextCol = cur.first
//    var nextRow = cur.second
//
//    val nextPlusLine: (Int) -> Int = { c ->
//        var result = (c + (dist % n))
//        if(result >= n) result = result - n
//        result
//    }
//    val nextMinusLine: (Int) -> Int = { c ->
//        var result = (c - (dist % n))
//        if(result < 0) result = n + result
//        result
//    }
//
//    when(dir) {
//        0 -> {
//            nextCol = nextMinusLine(nextCol)
//        }
//        1 -> {
//            nextCol = nextMinusLine(nextCol)
//            nextRow = nextPlusLine(nextRow)
//        }
//        2 -> {
//            nextRow = nextPlusLine(nextRow)
//        }
//        3 -> {
//            nextCol = nextPlusLine(nextCol)
//            nextRow = nextPlusLine(nextRow)
//        }
//        4 -> {
//            nextCol = nextPlusLine(nextCol)
//        }
//        5 -> {
//            nextCol = nextPlusLine(nextCol)
//            nextRow = nextMinusLine(nextRow)
//        }
//        6 -> {
//            nextRow = nextMinusLine(nextRow)
//        }
//        7 -> {
//            nextCol = nextMinusLine(nextCol)
//            nextRow = nextMinusLine(nextRow)
//        }
//    }
    val d = dVector[dir]
    val nextCol = (cur.first + d.first * dist % n + n) % n
    val nextRow = (cur.second + d.second * dist % n + n) % n

    return Pair(nextCol,nextRow)
}

fun getDirectionGroup(dir: Int, compDir: Int): Int {
    if(compDir < 0) {
        if(compDir == -1 && dir % 2 == 0) return -1
        if(compDir == -2 && dir % 2 == 1) return -2
    }
    else {
        if(dir % 2 == 0 && compDir % 2 == 0 ) return -1
        if(dir % 2 == 1 && compDir % 2 == 1) return -2
    }
    return -3
}

fun printBallMap() {
    ball.forEach {
        println(it.joinToString(" "))
    }
    println()
}

