import kotlin.math.pow

val k = readln().toInt()

fun main() {
    var n = 0
    var s = 0
    var e = 0
    while(k !in s..e) {
        s += 2.0.pow(n).toInt()
        e = s + 2.0.pow(n+1).toInt() - 1
        n += 1
    }
    val length = n
    var top = '4'.copyUntil(length)
    var bottom = '7'.copyUntil(length)
    var midTopK = (s+e)/2
    var midBottomK = (s+e)/2 + 1
    var headerLength = 1
    var midTop = top.slice(0 until headerLength) + '7'.copyUntil(length - headerLength)
    var midBottom = bottom.slice(0 until headerLength) + '4'.copyUntil(length - headerLength)

    while(k != midTopK && k != midBottomK) {
        headerLength += 1
        if(k in s..midTopK) {
            e = midTopK
            bottom = midTop
        }
        else {
            s = midBottomK
            top = midBottom
        }
        midTopK = (s+e)/2
        midBottomK = (s+e)/2 + 1
        midTop = top.slice(0 until headerLength) + '7'.copyUntil(length - headerLength)
        midBottom = bottom.slice(0 until headerLength) + '4'.copyUntil(length - headerLength)
    }
    if(midTopK == k) println(midTop)
    else println(midBottom)
}

fun Char.copyUntil(length: Int): String {
    return List(length) { this }.joinToString("")
}

