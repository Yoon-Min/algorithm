fun main() = with(System.`in`.bufferedReader()) {

    repeat(readLine().toInt()) {
        val n = readLine().toInt()
        val arr = List(n) { readLine().split(" ").map { it.toInt() } }.sortedBy { it.first() }
        var max = arr[0][1]
        var cnt = 0

        for(i in arr.indices) {
            if(arr[i][1] <= max) {
                cnt += 1
                max = arr[i][1]
            }
        }

        println(cnt)
    }
}