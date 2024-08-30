import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.*

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val blockMap = mutableListOf<MutableList<BlockStatus>>()
    var score = 0

    for (x in 0..<n) {
        val tmp = mutableListOf<BlockStatus>()
        val line = readln().split(" ").map { it.toInt() }
        for (y in 0..<n) {
            tmp.add(BlockStatus(x, y, line[y]))
        }
        blockMap.add(tmp)
    }
    while (true) {
        val bestBlockGroup = getBestBlockGroup(blockMap) ?: break

//        printMap(blockMap)

        deleteBlockGroup(blockMap, bestBlockGroup.groupLocationList)

//        printMap(blockMap)

        score += bestBlockGroup.totalBlock * bestBlockGroup.totalBlock
        val blockGroupRange = getBlockGroupRange(bestBlockGroup.groupLocationList, n)
        setGravity(blockGroupRange.first, blockGroupRange.second, blockMap)

//        printMap(blockMap)

        rotate(blockMap)

//        printMap(blockMap)

        setGravity(blockMap.indices, blockMap.indices, blockMap)

//        printMap(blockMap)
//        println("complete cycle")
    }
    println(score)
}

fun getBestBlockGroup(blockMap: MutableList<MutableList<BlockStatus>>): BlockGroup? {
    val blockGroups = mutableListOf<BlockGroup>()
    for (x in blockMap.indices) {
        for (y in blockMap.indices) {
            if (blockMap[x][y].code > 0) {
                getBlockGroup(blockMap[x][y], blockMap)?.let {
                    blockGroups.add(it)
                }
            }
        }
    }
    blockGroups.sortWith(
        compareBy(
            { -1 * it.totalBlock },
            { -1 * it.totalRainbowBlock },
            { -1 * it.standardBlock.x },
            { -1 * it.standardBlock.y }
        )
    )
    return if (blockGroups.isEmpty()) null else blockGroups.first()
}

fun getBlockGroup(standardBlock: BlockStatus, blockMap: MutableList<MutableList<BlockStatus>>): BlockGroup? {
    val nextDestination = listOf(listOf(0, 1), listOf(1, 0), listOf(0, -1), listOf(-1, 0))
    val isVisit = MutableList(blockMap.size) { MutableList(blockMap.size) { false } }
    val q = ArrayDeque<BlockGroup>()
    q.add(
        BlockGroup(
            groupLocationList = listOf(standardBlock),
            totalBlock = 1,
            totalRainbowBlock = if (standardBlock.code == 0) 1 else 0,
            standardBlock = standardBlock
        )
    )
    isVisit[standardBlock.x][standardBlock.y] = true

    while (q.isNotEmpty()) {
        val curGroup = q.removeFirst()
        val curBlock = curGroup.groupLocationList.last()

        for (destination in nextDestination) {
            val nextX = curBlock.x + destination[0]
            val nextY = curBlock.y + destination[1]
            if (!isValidRange(nextX, nextY, blockMap.indices)) continue
            if(isVisit[nextX][nextY]) continue

            val nextBlock = blockMap[nextX][nextY]
            if (nextBlock.code > -1 && (nextBlock.code == standardBlock.code || nextBlock.code == 0)) {
                isVisit[nextBlock.x][nextBlock.y] = true
                q.addLast(
                    curGroup.copy(
                        groupLocationList = curGroup.groupLocationList + listOf(nextBlock),
                        totalBlock = curGroup.totalBlock + 1,
                        totalRainbowBlock = if (nextBlock.code == 0) curGroup.totalRainbowBlock + 1 else curGroup.totalRainbowBlock
                    )
                )
            }
        }
    }

    val groupBlockList = mutableListOf<BlockStatus>()
    for(x in blockMap.indices) {
        for(y in blockMap.indices) {
            if(isVisit[x][y]) {
                groupBlockList.add(blockMap[x][y])
            }
        }
    }

    val largestGroup = BlockGroup(
        groupLocationList =  groupBlockList,
        totalBlock = groupBlockList.size,
        totalRainbowBlock = groupBlockList.filter { it.code == 0 }.size,
        standardBlock = groupBlockList.filter { it.code > 0 }.sortedWith(compareBy({it.x}, {it.y})).first()
    )

    return if(largestGroup.totalBlock < 2) null else largestGroup
}

fun deleteBlockGroup(blockMap: MutableList<MutableList<BlockStatus>>, groupLocationList: List<BlockStatus>) {
    groupLocationList.forEach { block ->
        blockMap[block.x][block.y] = block.copy(code = -2) // -2 : Empty
    }
}

fun getBlockGroupRange(groupLocationList: List<BlockStatus>, n: Int): Pair<IntRange, IntRange> {
    val sortedByY = groupLocationList.sortedBy { it.y }
    val xRange = (0..<n)
    val yRange = (sortedByY.first().y..sortedByY.last().y)
    return Pair(xRange, yRange)
}

fun setGravity(colRange: IntRange, rowRange: IntRange, blockMap: MutableList<MutableList<BlockStatus>>) {
    for (x in colRange.reversed()) {
        for (y in rowRange.reversed()) {
            if(blockMap[x][y].code > -1) {
                var nextColIndex = x + 1
                while (nextColIndex <= colRange.last) {
                    val nextBlock = blockMap[nextColIndex][y]
                    val curBlock = blockMap[nextColIndex - 1][y]
                    if (nextBlock.code == -2) {
                        blockMap[nextColIndex][y] = nextBlock.copy(code = curBlock.code)
                        blockMap[nextColIndex-1][y] = curBlock.copy(code = -2)
                    } else{
                        break
                    }
                    nextColIndex += 1
                }
            }

        }
    }
}

fun rotate(blockMap: MutableList<MutableList<BlockStatus>>) {
    val rotatedMap = (blockMap.lastIndex downTo 0).map { i -> blockMap.map { it[i] } }
    for (x in blockMap.indices) {
        for (y in blockMap.indices) {
            blockMap[x][y] = rotatedMap[x][y].copy(x = x, y = y)
        }
    }
}

fun isValidRange(x: Int, y: Int, validRange: IntRange): Boolean {
    return x in validRange && y in validRange
}

fun printMap(blockMap: MutableList<MutableList<BlockStatus>>) {
    blockMap.forEach { blockList ->
        println(blockList.map { it.code.toString() }.map { if(it == "-2") " " else it})
    }
    println()
}

data class BlockStatus(
    val x: Int,
    val y: Int,
    val code: Int,
)

data class BlockGroup(
    val groupLocationList: List<BlockStatus>,
    val totalBlock: Int,
    val totalRainbowBlock: Int,
    val standardBlock: BlockStatus
)