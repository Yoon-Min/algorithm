import java.util.*
import kotlin.math.*

val n = readln().toInt()
val a = readln().split(" ").map { it.toInt() }
val m = readln().toInt()
val b = readln().split(" ").map { it.toInt() }
val aSorted = a.mapIndexed { i,e -> Pair(e,i) }.sortedByDescending { it.first }
var result = mutableListOf<Int>()

fun main() {
    rec(0,0, mutableListOf())
    println(result.size)

    if(result.isNotEmpty()) {
        println(result.joinToString(" "))
    }
}

fun rec(aI: Int, bI: Int, tmpStack: MutableList<Int>) {
    if(aI >= a.size || bI >= b.size) {
        return
    }
    var starting = aI
    for(p in aSorted) {
        val aMax = p.first
        val aMaxIndex = p.second
        var isFind = false
        if(aMaxIndex !in aI..a.lastIndex) continue
        for(i in bI..b.lastIndex) {
            if(b[i] == aMax) {
                starting = aMaxIndex
                isFind = true
                break
            }
        }
        if(isFind) break
    }
    var completedMaxNum = 0
    for(i in starting..a.lastIndex) {
        val standard = a[i]
        if(standard <= completedMaxNum) continue
        for(j in bI..b.lastIndex) {
            if(standard == b[j]) {
                tmpStack.add(standard)
                updateResult(tmpStack)
                rec(i+1, j+1, tmpStack)
                tmpStack.removeLast()
                completedMaxNum = standard
                break
            }
        }
    }
}

fun List<Int>.maxWithin(range: IntRange): Pair<Int,Int> {
    var maxIndex = 0
    var maxNum = 0
    for(i in range) {
        if(maxNum < this[i]) {
            maxNum = this[i]
            maxIndex = i
        }
    }
    return Pair(maxNum,maxIndex)
}

fun updateResult(com: MutableList<Int>) {
    val minSize = min(result.size, com.size)
    var isNeedToUpdate = false
    for(i in 0 until minSize) {
        if(result[i] < com[i]) {
            isNeedToUpdate = true
            break
        }
    }
    if(!isNeedToUpdate && com.size > minSize) {
        isNeedToUpdate = true
    }
    if(isNeedToUpdate) {
        val new = com.joinToString(" ")
        result.clear()
        for(e in com) result.add(e)
    }
}

