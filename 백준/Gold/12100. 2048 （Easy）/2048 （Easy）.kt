import kotlin.math.*

val map = mutableListOf<List<Int>>()
val sequenceList = listOf(0,1,2,3)
val sequenceCaseList = mutableListOf<List<Int>>()
const val MAX_SEQUENCE = 5

fun main() {
    setSequenceCase(mutableListOf())
    val n = readln().toInt()
    repeat(n) {
        val inputLine = readln().split(" ").map { it.toInt() }
        map.add(inputLine)
    }

    var max = 0
    val secondScopeList = listOf((n-1 downTo 0), (0..< n), (0..< n), (n-1 downTo 0))
    sequenceCaseList.forEach { sl ->
        val tmpMap = map.map { inner -> inner.toMutableList() }
        sl.forEach { direction ->
            moveBlocks(n, direction, secondScopeList[direction], tmpMap)
        }
        for(i in 0..<n) {
            for(j in 0..<n) {
                max = max(max, tmpMap[i][j])
            }
        }
    }
    println(max)
}

fun moveBlocks(n: Int, direction: Int, secondScope: IntProgression, map: List<MutableList<Int>>) {
    for(i in 0..< n) {
        val blockList = ArrayDeque<Int>()
        for(j in secondScope) {
            val blockPos = when(direction) {
                0,2 -> Pair(j,i)
                else -> Pair(i,j)
            }
            val block = map[blockPos.first][blockPos.second]
            if(block > 0) {
                blockList.addFirst(block)
                map[blockPos.first][blockPos.second] = 0
            }
        }
        if(blockList.isEmpty()) continue

        val tmpStack = ArrayDeque<Int>()
        val resultBlockList = ArrayDeque<Int>()

        while(blockList.isNotEmpty()) {
            if(tmpStack.isEmpty() || blockList.first() != tmpStack.last()) tmpStack.add(blockList.removeFirst())
            else {
                val unionBlock = blockList.removeFirst() + tmpStack.removeLast()
                while(tmpStack.isNotEmpty()) { resultBlockList.add(tmpStack.removeFirst()) }
                resultBlockList.add(unionBlock)
            }
        }
        while(tmpStack.isNotEmpty()) {
            resultBlockList.add(tmpStack.removeFirst())
        }

        for(k in secondScope.reversed()) {
            val nextBlockPos = when(direction) {
                0,2 -> Pair(k,i)
                else -> Pair(i,k)
            }
            map[nextBlockPos.first][nextBlockPos.second] = resultBlockList.removeFirst()
            if(resultBlockList.isEmpty()) break
        }
    }
}

fun setSequenceCase(tmpList: MutableList<Int>) {
    if(tmpList.size == MAX_SEQUENCE) {
        sequenceCaseList.add(tmpList.toList())
        return
    }
    sequenceList.forEach { s ->
        tmpList.add(s)
        setSequenceCase(tmpList)
        tmpList.removeLast()
    }
}

// Testcase
/*
4
2 0 2 8
0 0 2 2
0 0 0 0
0 0 0 0

4
0 0 2 0
0 0 0 0
2 0 0 0
0 0 0 0

3
2 2 2
4 4 4
8 8 8
 */





