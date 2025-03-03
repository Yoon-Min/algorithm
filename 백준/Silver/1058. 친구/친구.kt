import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val friends = List(n) { mutableSetOf<Int>() }
    var maxCount = 0
    repeat(n) { i ->
        readLine().forEachIndexed { j, e ->
            if(j != i && e == 'Y') {
                friends[i].add(j)
            }
        }
    }
    for(cur in 0 until n) {
        var count = 0
        for(next in 0 until n) {
            if(next == cur) continue
            if(friends[cur].contains(next)) {
                count += 1
                continue
            }
            for(friend in friends[cur]) {
                if(friends[next].contains(friend)) {
                    count += 1
                    break
                }
            }
        }
        maxCount = max(maxCount, count)
    }
    println(maxCount)
}

