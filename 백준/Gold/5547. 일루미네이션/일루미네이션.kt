import kotlin.math.*
import kotlin.system.exitProcess

val dVectorForOdd = listOf(Pair(0,1), Pair(1,0), Pair(0,-1), Pair(-1,0), Pair(1,-1), Pair(-1,-1))
val dVectorForEven = listOf(Pair(0,1), Pair(1,0), Pair(0,-1), Pair(-1,0), Pair(-1,1), Pair(1,1))
/*
  3: 탐색 완료된 건물 공간
  2: 외부 빈 공간
  1: 탐색되지 않은 건물 공간
  0: 아직 탐색되지 않은 빈 공간
  -1: 내부 빈 공간
 */

fun main() = with(System.`in`.bufferedReader()) {
    val (w,h) = readLine().split(" ").map { it.toInt() }
    val map = List(h) { readLine().split(" ").map { it.toInt() }.toMutableList() }
    var total = 0

    // 1. 모든 빈 공간을 내부 혹은 외부 공간으로 구분
    for(i in 0 until h) {
        for(j in 0 until w) {
            if(map[i][j] == 0) {
                val q = ArrayDeque<Pair<Int,Int>>().also { it.add(Pair(i,j))}
                val s = mutableSetOf<Pair<Int,Int>>().also { it.add(Pair(i,j)) }
                var isOutside = false
                map[i][j] = -1

                while(q.isNotEmpty()) {
                    val cur = q.removeFirst()
                    if(cur.first == 0 || cur.first == h-1 || cur.second == 0 || cur.second == w-1) {
                       isOutside = true
                    }
                    val nextDVector = if(cur.first%2 == 0) dVectorForEven else dVectorForOdd
                    nextDVector.forEach { v ->
                        val nx = v.second + cur.second
                        val ny = v.first + cur.first
                        if(nx in 0 until w && ny in 0 until h && map[ny][nx] == 0) {

                            s.add(Pair(ny,nx))
                            q.add(Pair(ny,nx))
                            map[ny][nx] = -1
                        }
                    }
                }
                if(isOutside) {
                    s.forEach { p ->
                        map[p.first][p.second] = 2
                    }
                }
            }
        }
    }
    // 2. 그룹 하나씩 찾아서 외부에 설치할 조명 개수 계산
    for(i in 0 until h) {
        for(j in 0 until w) {
            if(map[i][j] == 1) {
                val q = ArrayDeque<Pair<Int,Int>>().also { it.add(Pair(i,j)) };
                map[i][j] = 3
                while(q.isNotEmpty()) {
                    val cur = q.removeFirst()
                    val nextDVector = if(cur.first%2 == 0) dVectorForEven else dVectorForOdd
                    nextDVector.forEach { v ->
                        val nx = v.second + cur.second
                        val ny = v.first + cur.first
                        if(nx in 0 until w && ny in 0 until h) {
                            when(map[ny][nx]) {
                                1 -> {
                                    q.add(Pair(ny,nx))
                                    map[ny][nx] = 3
                                }
                                2 -> total += 1
                            }
                        }
                        else {
                            total += 1
                        }
                    }
                }
            }
        }
    }
    println(total)
}

