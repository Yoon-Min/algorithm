fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val arr = readLine().split(" ").map { it.toInt() }.sorted()
    val range = MutableList(40001) { Pair(0, 0) }
    val exist = MutableList(40001) { false }
    var result = 0L

    var i = 0
    var prev = arr[i]
    while (i < n) {
        val num = arr[i]
        exist[num.toIndex()] = true
        if (prev != num) {
            range[prev.toIndex()] = range[prev.toIndex()].copy(second = i - 1)
            range[num.toIndex()] = range[num.toIndex()].copy(first = i)
        }
        prev = num
        i += 1
    }
    range[prev.toIndex()] = range[prev.toIndex()].copy(second = i - 1)

    for (i in 0 until n) {
        for (j in i + 1 until n) {
            val target = -(arr[i] + arr[j])
            if (!exist[target.toIndex()]) continue

            val k = range[target.toIndex()]
            if (k.second < j) continue
            result += if (j in k.first..k.second) {
                k.second - j
            } else {
                k.second - k.first + 1
            }
        }
    }
    println(result)
}

fun Int.toIndex(): Int {
    return if (this < 0) -this + 20000 else this
}