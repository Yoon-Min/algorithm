import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val arr = readLine().split(" ").map { it.toInt() }
    val m = hashMapOf<Int, MutableSet<Int>>()
    val s = arr.toSet()
    val result = mutableSetOf<Int>()
    var count = 0

    for(i in arr.indices) {
        m[arr[i]]?.add(i) ?: m.set(arr[i], mutableSetOf(i))
    }

    for(i in arr.indices) {
        for(j in i+1..arr.lastIndex) {
            val sum = arr[i] + arr[j]

            if(!s.contains(sum)) continue

            val containsI = m[sum]!!.contains(i)
            val containsJ = m[sum]!!.contains(j)

            if(containsI) m[sum]!!.remove(i)
            if(containsJ) m[sum]!!.remove(j)

            if(m[sum]!!.isNotEmpty()) {
//                println("$i $j $sum")
                count += m[sum]!!.size
                m[sum]!!.clear()
            }

            if(containsI) m[sum]!!.add(i)
            if(containsJ) m[sum]!!.add(j)
        }
    }

    println(count)
}