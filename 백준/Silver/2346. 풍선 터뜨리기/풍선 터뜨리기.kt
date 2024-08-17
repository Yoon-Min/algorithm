import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.*

//val bw = BufferedWriter(OutputStreamWriter(System.out))

fun main() {
    val n = readln().toInt()
    val arr = readln().split(" ").map { it.toInt() }.toMutableList()
    val result = mutableListOf<Int>()
    var i = 0
    while (true) {
        val step = arr[i]
        var counter = 0
        arr[i] = Int.MAX_VALUE
        result.add(i+1)

        if(result.size == arr.size) break

        while(counter < abs(step)) {
            if(step > 0) {
                i = (i + 1) % arr.size
            }
            else if(step < 0) {
                i = if(i == 0) arr.lastIndex else i-1
            }
            if (arr[i] != Int.MAX_VALUE) {
                counter += 1
            }
        }
    }
    println(result.joinToString(" "))
}
