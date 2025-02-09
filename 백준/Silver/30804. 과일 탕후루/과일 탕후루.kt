import kotlin.math.*

fun main() {
    val n = readln().toInt()
    val input = readln().split(" ").map { it.toInt() }
    val q = ArrayDeque<Int>().also { it.add(input.first()) }
    var maxLength = 1
    var f = 0
    var s = 0
    for(i in 1..input.lastIndex) {
        val num = input[i]
        val prevNum = input[i-1]

        if(prevNum != num) {
            if(q.contains(num)) {
                s = i
            }
            else if(q.size < 2) {
                s = i
                q.add(num)
            }
            else {
                if(input[f] == input[s]) {
                    q.clear()
                    q.add(input[f])
                }
                else q.remove(input[f])
                f = s
                q.add(num)
                s = i
            }
        }
        maxLength = max(maxLength, i-f+1)
    }
    println(maxLength)
}



