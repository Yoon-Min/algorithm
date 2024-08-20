import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.*

//val bw = BufferedWriter(OutputStreamWriter(System.out))
val br = BufferedReader(InputStreamReader(System.`in`))
val state = mutableListOf<Int>()

fun main() {
    while(true) {
        val str : String = br.readLine() ?: break
        val total = 3.0.pow(str.toInt()).toInt()
        state += List(total) { 1 }
        setApproximation(0, state.lastIndex)
        println(state.joinToString("") { if (it == 1) "-" else " " })
        state.clear()
    }
}

fun setApproximation(i: Int, j: Int) {
    if(j-i < 2) return
    val total = j-i + 1
    val s = i + total/3
    val e = s + (total-3)/3
    for(k in s..e) { state[k] = 0 }
    setApproximation(i,s-1)
    setApproximation(e+1, j)
}

//fun bufferPrint(str: String) {
//    bw.write("$str\n")
//}
