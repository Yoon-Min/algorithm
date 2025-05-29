fun main() = with(System.`in`.bufferedReader()) {
    val (a,b) = readLine().split(" ")
    val aList = a.map { it.code - 48 }
    val bList = b.map { it.code - 48 }

    val min = aList.map { if(it == 6) 5 else it }.joinToString("").toInt() + bList.map { if(it == 6) 5 else it }.joinToString("").toInt()
    val max = aList.map { if(it == 5) 6 else it }.joinToString("").toInt() + bList.map { if(it == 5) 6 else it }.joinToString("").toInt()

    println("$min $max")
}