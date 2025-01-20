import kotlin.math.*
import kotlin.system.exitProcess

val directionVector = listOf(
    listOf(0,1), listOf(1,0), listOf(0,-1), listOf(-1,0), listOf(1,1), listOf(-1,-1), listOf(-1,1), listOf(1,-1)
)

fun main() {
    val n = readln().toInt()
    val resultMap = mutableListOf<List<Int>>()
    val progressedMap = mutableListOf<MutableList<Int>>()
    val trapPoint = mutableSetOf<Pair<Int,Int>>()

    repeat(n) {
        resultMap.add(readln().map { if(it == '.') -1 else -2 })
    }
    repeat(n) {
        progressedMap.add(readln().map { if(it == '.') -1 else 1}.toMutableList() )
    }

    var isFindTrap = false
    for(i in 0..<n) {
        for(j in 0..<n) {
            if(resultMap[i][j] == -2) trapPoint.add(Pair(i,j))
            if(progressedMap[i][j] == 1) {
                if(resultMap[i][j] == -2) {
                    progressedMap[i][j] = -2
                    isFindTrap = true
                    continue
                }
                var trapCount = 0
                directionVector.forEach { v ->
                    val nextI = i + v.first()
                    val nextY = j + v.last()
                    if(nextI in 0..<n && nextY in 0..<n && resultMap[nextI][nextY] == -2) {
                        trapCount += 1
                    }
                }
                progressedMap[i][j] = trapCount
            }
        }
    }
    if(isFindTrap) {
        trapPoint.forEach { p ->
            progressedMap[p.first][p.second] = -2
        }
    }
    progressedMap
        .map { inner -> inner.map { if(it == -1) '.' else if(it == -2) '*' else (48+it).toChar()}}
        .forEach { println(it.joinToString("")) }
}



