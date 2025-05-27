fun main() = with(System.`in`.bufferedReader()) {
    val arr = readLine().toList().map { it.code - 48 }.sortedDescending()
    if(arr.dropWhile { it == 0 }.sum() % 3 != 0) println(-1)
    else if(arr.last() != 0) println(-1)
    else println(arr.joinToString(""))
}