import kotlin.math.*

fun main() {
    val (n,m,k) = readln().split(" ").map { it.toInt() }
    var max = 0
    for(i in 0..k) {
        val femaleIntern = n - i
        val maleIntern = m - (k - i)
        if(femaleIntern < 2 || maleIntern < 1) {
            continue
        }
        var cur = 1
        var count = 0
        while(cur <= maleIntern && cur*2 <= femaleIntern) {
            cur += 1
            count += 1
        }
        max = max(max, count)
    }
    println(max)
}

