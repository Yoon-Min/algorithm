fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val number = readLine().split(" ").map { it.toInt() }
    val score = MutableList(1000001) {0}
    val exist = MutableList(1000001) {0}.also {
        number.forEach { n ->
            it[n] = 1
        }
    }

    val maxNum = number.max()
    for(i in number.indices) {
        val currN = number[i]
        for(nextN in currN*2..maxNum step currN) {
            if(exist[nextN] == 1) {
                score[currN] += 1
                score[nextN] -= 1
            }
        }
    }
    val sb = StringBuilder()
    for(currN in number) {
        sb.append("${score[currN]} ")
    }
    println(sb)
}



