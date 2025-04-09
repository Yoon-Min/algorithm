import kotlin.math.*

val dVector = listOf(Pair(0, 1), Pair(1, 0), Pair(0, -1), Pair(-1, 0))
val n = readln().toInt()
val map  = List(n) { readln().split(" ").map { it.toInt() * -1 }.toMutableList() }
val groupCornerPos = mutableListOf<List<Pair<Int,Int>>>(listOf())
var groupNumber = 1
var result = Int.MAX_VALUE

fun main() = with(System.`in`.bufferedReader()) {
    setGroupNumber()
    for(group in 1 until groupNumber) {
        for(pos in groupCornerPos[group]) {
            calculateGroupMinDistance(pos, group)
        }
    }
    println(result)
}


fun calculateGroupMinDistance(initPos: Pair<Int,Int>, group: Int) {
    val q = ArrayDeque<Triple<Int,Int,Int>>().also { it.add(Triple(initPos.first,initPos.second,0)) }
    val dist = List(n) { MutableList(n) {0} }

    while(q.isNotEmpty()) {
        val cur = q.removeFirst()
        val nextDist = cur.third+1

        for(v in dVector) {
            val nx = v.second + cur.second
            val ny = v.first + cur.first

            if(nx !in map.indices || ny !in map.indices) continue
            if(map[ny][nx] > 0 && map[ny][nx] != group) {
                result = min(result, cur.third)
                return
            }
            if(map[ny][nx] == 0 && dist[ny][nx] == 0 && nextDist < result) {
                dist[ny][nx] = nextDist
                q.add(Triple(ny,nx,nextDist))
            }
        }
    }
}

fun setGroupNumber() {
    for(i in map.indices) {
        for(j in map.indices) {
            if(map[i][j] == -1) {
                val q = ArrayDeque<Pair<Int,Int>>().also { it.add(Pair(i,j)) }
                val cornerPos = mutableListOf<Pair<Int,Int>>()
                map[i][j] = groupNumber

                while(q.isNotEmpty()) {
                    val cur = q.removeFirst()
                    var isCornerPos = false

                    for(v in dVector ) {
                        val nx = v.second + cur.second
                        val ny = v.first + cur.first
                        if(nx !in map.indices || ny !in map.indices) continue
                        if(map[ny][nx] == 0) {
                            isCornerPos = true
                            continue
                        }
                        if( map[ny][nx] == -1) {
                            q.add(Pair(ny,nx))
                            map[ny][nx] = groupNumber
                        }
                    }
                    if(isCornerPos) cornerPos.add(cur)
                }
                groupCornerPos.add(cornerPos)
                groupNumber += 1
            }
        }
    }
}