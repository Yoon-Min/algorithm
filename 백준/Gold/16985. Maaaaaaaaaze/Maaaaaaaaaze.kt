import kotlin.math.*

val map = List(25) { readln().split(" ").map { it.toInt() } }
val flatSeqCase = mutableListOf<List<Int>>()
val rotationCase = mutableListOf<List<Int>>()

val dVector = listOf(
    Triple(0,1,0),
    Triple(1,0,0),
    Triple(0,-1,0),
    Triple(-1,0,0),
    Triple(0,0,1),
    Triple(0,0,-1)
)

val vertex = listOf(
    Triple(0, 0, 0),
    Triple(0, 4, 0),
    Triple(4, 0, 0),
    Triple(4, 4, 0),
    Triple(0, 0, 4),
    Triple(0, 4, 4),
    Triple(4, 0, 4),
    Triple(4, 4, 4)
)

val aislePair = vertex.zip(vertex.reversed())

fun main() = with(System.`in`.bufferedReader()) {
    var minDist = Int.MAX_VALUE

    setFlatSequenceSet()
    setRotationCase()

    val rotatedMap = List(5) { i ->
        List(4) { r ->
            List(5) { j -> List(5) { k -> map[i*5 + j][k] } }.rotate(r)
        }
    }

    val finishedCube = mutableSetOf<String>()

    for (flatSeq in flatSeqCase) {
        for (rotateCountSet in rotationCase) {

            val threeDimMap = flatSeq.map { i -> rotatedMap[i][rotateCountSet[i]] }

            val s = Triple(0,0,0)
            val e = Triple(4,4,4)
            if(threeDimMap[s.first][s.second][s.third] == 0 || threeDimMap[e.first][e.second][e.third] == 0) continue

            val q = ArrayDeque<Node>().also { it.add(Node(s.first,s.second,s.third,0)) }
            val visited = List(5) { List(5) { MutableList(5) {false} } }.also { it[s.first][s.second][s.third] = true }

            while(q.isNotEmpty()) {
                val cur = q.removeFirst()

                if(cur.isMatchPosition(e)) {
                    minDist = min(minDist, cur.dist)
                    break
                }

                for(v in dVector) {
                    val ni = v.first + cur.i
                    val nj = v.second + cur.j
                    val nk = v.third + cur.k

                    if(ni !in 0 until 5 || nj !in 0 until 5 || nk !in 0 until 5) continue

                    if(threeDimMap[ni][nj][nk] == 1 && !visited[ni][nj][nk]) {
                        visited[ni][nj][nk] = true
                        q.add(Node(ni,nj,nk,cur.dist+1))
                    }
                }
            }
        }
    }

    if(minDist == Int.MAX_VALUE) println(-1)
    else println(minDist)
}

fun setFlatSequenceSet(
    s: Int = 0,
    tmp: MutableList<Int> = mutableListOf(),
    visited: MutableList<Boolean> = MutableList(5) {false}
) {
    if (5 == tmp.size) {
        flatSeqCase.add(tmp.toList())
        return
    }
    for (i in 0 until 5) {
        if(!visited[i]) {
            visited[i] = true
            tmp.add(i)
            setFlatSequenceSet(i + 1, tmp, visited)
            tmp.removeLast()
            visited[i] = false
        }

    }
}

fun setRotationCase(
    tmp: MutableList<Int> = mutableListOf()
) {
    if (5 == tmp.size) {
        rotationCase.add(tmp.toList())
        return
    }
    for (i in 0 until 4) {
        tmp.add(i)
        setRotationCase(tmp)
        tmp.removeLast()
    }
}

fun List<List<Int>>.rotate(rotationCount: Int): List<List<Int>> {
    if (rotationCount == 0) return this
    if (this.sumOf { it.count { num -> this[0][0] == num} } == 25) return this

    var rotated = this
    repeat(rotationCount) {
        rotated = (0 until this.first().size).map { j ->
            indices.reversed().map { i ->
                rotated[i][j]
            }
        }
    }
    return rotated
}

data class Node(
    val i: Int,
    val j: Int,
    val k: Int,
    val dist: Int
) {
    fun isMatchPosition(pos: Triple<Int,Int,Int>): Boolean {
        return pos.first == i && pos.second == j && pos.third == k
    }
}