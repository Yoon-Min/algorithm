import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n,d,k,c) = readLine().split(" ").map { it.toInt() }
    val belt = mutableListOf<Int>()
    val visited = BooleanArray(d+1)
    var result = 0

    repeat(n) {
        belt.add(readLine().toInt())
    }

    for(i in 0 until n ) {
        var duplicated = 0
        var notExistEventSushi = 1
        visited.fill(false)

        for(j in i until i+k) {
            if(!visited[belt[j % n]]) {
                visited[belt[j % n]] = true
            }
            else {
                duplicated += 1
            }

            if(belt[j % n] == c) {
                notExistEventSushi = 0
            }
        }

        result = max(result, k - duplicated + notExistEventSushi)
    }
    println(result)
}