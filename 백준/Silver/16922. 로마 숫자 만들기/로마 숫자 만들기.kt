import kotlin.math.*

val unit = listOf(1,5,10,50)
val checked = MutableList(20*50+1) { false }

fun main() {
    val n = readln().toInt()
    comb(0, n, 0)
    println(checked.count { it })
}

fun comb(s: Int, size: Int, sum: Int, cnt: Int = 0) {
    if(cnt == size) {
        checked[sum] = true
        return
    }
    for(i in s..unit.lastIndex) {
        comb(i, size, sum + unit[i], cnt+1)
    }
}