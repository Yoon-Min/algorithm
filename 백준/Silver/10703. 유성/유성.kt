import kotlin.math.*
import kotlin.system.exitProcess

var sb = StringBuilder()

fun main() = with(System.`in`.bufferedReader()) {
    val (r,s) = readLine().split(" ").map { it.toInt() }
    val map = MutableList(r) { MutableList(s) {'.'} }
    val meteorIndex = mutableListOf<MutableList<Int>>()

    repeat(r) { i ->
        val line = readLine().toMutableList()
        val tmp = mutableListOf<Int>()
        var isMeteorLine = false
        for(j in line.indices) {
            if(line[j] == 'X') {
                isMeteorLine = true
                tmp.add(j)
                map[i][j] = '.'
            }
            else {
                map[i][j] = line[j]
            }
        }
        if(isMeteorLine) meteorIndex.add(tmp)
    }

    var bottomIndex = map.lastIndex - 1
    var i = bottomIndex
    var j = meteorIndex.lastIndex
    var counter = 0
    while(true) {
        if(checkValidation(map[i], meteorIndex[j])) {
            counter += 1
            j -= 1
            i -= 1
        }
        else {
            counter = 0
            bottomIndex -= 1
            i = bottomIndex
            j = meteorIndex.lastIndex
            continue
        }

        if(counter == meteorIndex.size) {
            i += 1
            for(k in i..bottomIndex) {
                val meteorLine = meteorIndex[k-i]
                meteorLine.forEach {
                    map[k][it] = 'X'
                }
            }
            break
        }
    }

    for(k in map.indices) {
        sb.append(map[k].joinToString("")).append('\n')
    }
    print(sb.toString())
}

fun checkValidation(mapLine: List<Char>, meteorLine: List<Int>): Boolean {
    meteorLine.forEach { meteorIndex ->
        if(mapLine[meteorIndex] != '.') return false
    }
    return true
}

