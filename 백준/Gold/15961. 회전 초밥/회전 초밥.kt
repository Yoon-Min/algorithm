import kotlin.math.*

var joinCount = 0

fun main() = with(System.`in`.bufferedReader()) {
    val (n,d,k,c) = readLine().split(" ").map { it.toInt() }
    val belt = List(n) { readLine().toInt() }
    val sushiCount = MutableList(d+1) { 0 }
    var result = 0

    val s = mutableSetOf<Int>()
    var left = 0
    var right = k-1

    for(i in left..right) {
        s.add(belt[i])
        sushiCount[belt[i]] += 1
    }
    result = s.size
    if(!s.contains(c)) result += 1

    while(true) {
        if(sushiCount[belt[left]] == 1) {
            s.remove(belt[left])
        }
        sushiCount[belt[left]] -= 1
        left  = (left+1)%n
        right = (right+1)%n
        s.add(belt[right])
        sushiCount[belt[right]] += 1

        if(left == 0) break

        val cnt = s.size + if(s.contains(c)) 0 else 1
//        println("$cnt $left $right ${s}")
        result = max(result, cnt)
    }
    println(result)
}