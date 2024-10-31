import kotlin.math.*

fun main() {
    val (n,m) = readln().split(" ").map { it.toInt() }
    val map = mutableListOf<MutableList<Int>>()
    repeat(n) {
        map.add(readln().map { it.toString().toInt() }.toMutableList())
    }
    var curMaxLength = 1
    for(i in 0..<n) {
        for(j in 0..<m) {
            val maxLength = min(n-i,m-j)
            for(k in 0..<maxLength) {
                val curLength = k+1
                if(curLength <= curMaxLength) continue
                if(i+k in 0..<n && j+k in 0..<m) {
                    if(isEqual(map[i][j], map[i][j+k], map[i+k][j], map[i+k][j+k])) {
                        curMaxLength = curLength
                    }
                }
            }
        }
    }
    println(curMaxLength * curMaxLength)
}

fun isEqual(a: Int, b: Int, c: Int, d: Int): Boolean {
    return a == b && a == c && a == d
}


