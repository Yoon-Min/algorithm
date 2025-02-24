import kotlin.math.*

val dVector = listOf(Pair(-1, 0), Pair(0, 1), Pair(1, 0), Pair(0, -1))
val per = mutableListOf<List<Int>>()

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m, k) = readLine().split(" ").map { it.toInt() }
    val map = List(n) { readLine().split(" ").map { it.toInt() } }
    val rotationInfo = List(k) { readLine().split(" ").map { it.toInt() } }
    var min = Int.MAX_VALUE
    setPermutation(k, mutableListOf(), MutableList(k){false})

    per.forEach {
        val originMap = List(n) { i -> MutableList(m) { j -> map[i][j]} }
        val resultMap = List(n) { i -> MutableList(m) { j -> map[i][j] } }
        for(seq in it) {
            var completed = 0
            val info = rotationInfo[seq]
            var colRange = (info[0] - info[2] - 1)until (info[0] + info[2])
            var rowRange = (info[1] - info[2] - 1)until (info[1] + info[2])
            val total = colRange.count() * rowRange.count()
            while(completed < total) {
                for(i in rowRange.first until rowRange.last) {
                    resultMap[colRange.first][i+1]  = originMap[colRange.first][i]
                    completed += 1
                }
                for(i in colRange.first until colRange.last) {
                    resultMap[i+1][rowRange.last] = originMap[i][rowRange.last]
                    completed += 1
                }
                for(i in rowRange.last downTo rowRange.first+1) {
                    resultMap[colRange.last][i-1] = originMap[colRange.last][i]
                    completed += 1
                }
                for(i in colRange.last downTo colRange.first+1) {
                    resultMap[i-1][rowRange.first] = originMap[i][rowRange.first]
                    completed += 1
                }
                colRange = colRange.first+1 until colRange.last
                rowRange = rowRange.first+1 until rowRange.last
                if(colRange.count() * rowRange.count() == 1) {
                    completed += 1
                }
            }
            for(i in 0 until n) {
                for(j in 0 until m) {
                    originMap[i][j] = resultMap[i][j]
                }
            }
        }
        min = min(min, resultMap.minBy { inner -> inner.sum() }.sum())
    }
    println(min)
}

fun setPermutation(k: Int, tmp: MutableList<Int>, isVisited: MutableList<Boolean>) {
    if(tmp.size == k) {
        per.add(tmp.toList())
        return
    }
    for(i in 0 until k) {
        if(!isVisited[i]) {
            tmp.add(i)
            isVisited[i] = true
            setPermutation(k, tmp, isVisited)
            isVisited[i] = false
            tmp.removeLast()
        }
    }
}