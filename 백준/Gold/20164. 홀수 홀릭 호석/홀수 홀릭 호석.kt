import kotlin.math.*
import kotlin.system.exitProcess

var min = Int.MAX_VALUE
var max = 0
val input = readln()

fun main() {
    rec(input)
    println("$min $max")
}

fun rec(nStr: String, count: Int = 0) {
    val range = nStr.indices
    val s = range.first
    val e = range.last

    if(nStr.length == 1) {
        countOdd(nStr, range).let {
            min = min(min,count+it)
            max = max(max,count+it)
        }
        return
    }
    if(nStr.length == 2) {
        val nextCount = countOdd(nStr, s..s) + countOdd(nStr, e..e) + count
        val next = (nStr.first().code - 48) + (nStr.last().code - 48)
        rec(next.toString(), nextCount)
    }
    for(i in s..e-2) {
        for(j in i+1..< e) {
            val aR = s..i
            val bR = i+1..j
            val cR = j+1..e
            val nextCount = countOdd(nStr, aR) + countOdd(nStr, bR) + countOdd(nStr, cR) + count
            val next = nStr.toInt(aR) + nStr.toInt(bR) + nStr.toInt(cR)
            rec(next.toString(),nextCount)
        }
    }
}

fun countOdd(n: String, range: IntRange): Int {
    var count = 0
    for(i in range) {
        val curN = n[i].code - 48
        if(curN%2 == 1) count += 1
    }
    return count
}

fun String.toInt(range: IntRange): Int {
    var next = 10.0.pow(range.last - range.first).toInt()
    var sum = 0
    for(i in range) {
        sum += (this[i].code - 48) * next
        next /= 10
    }
    return sum
}



