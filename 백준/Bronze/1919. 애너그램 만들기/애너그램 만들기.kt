import kotlin.math.*

fun main() {
    val aCnt = MutableList(123) {0}
    val bCnt = MutableList(123) {0}
    val a = readln()
    val b = readln()
    var removal = 0

    a.forEach {
        aCnt[it.code] += 1
    }
    b.forEach {
        bCnt[it.code] += 1
    }

    (97..122).forEach {
        if(aCnt[it] > 0 && bCnt[it] > 0) {
            removal += min(aCnt[it],bCnt[it]) * 2
            aCnt[it] -= min(aCnt[it],bCnt[it])
            bCnt[it] -= min(aCnt[it],bCnt[it])
        }
    }

    println(a.length + b.length - removal)
}