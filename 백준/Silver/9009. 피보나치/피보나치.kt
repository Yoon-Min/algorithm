import kotlin.math.*

val fib = mutableListOf(0,1)

fun main() = with(System.`in`.bufferedReader()) {
    while(fib.last() <= 1000000000) {
        fib.add(fib[fib.lastIndex] + fib[fib.lastIndex-1])
    }
    fib.removeLast()

    repeat(readLine().toInt()) {
        var n = readLine().toInt()
        val result = mutableListOf<Int>()

        while (n != 0) {
            val nextFib = floorBinarySearch(n)
            result.add(nextFib)
            n -= nextFib
        }

        for (i in result.lastIndex downTo 0) {
            print("${result[i]} ")
        }
        println()
    }
}

fun floorBinarySearch(target: Int): Int {
    val idx = fib.binarySearch(target)
    return when {
        idx >= 0 -> fib[idx]  // 정확히 일치하는 경우
        else -> {
            val insertPoint = -idx - 1
            fib[insertPoint - 1]
        }
    }
}


