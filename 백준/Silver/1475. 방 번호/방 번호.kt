import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.*

fun main() {
    val n = readln()
    val numberStore = MutableList(10) { 1 }
    var result = 1
    n.forEach {
        var num = it.code - 48
        if(num == 6 || num == 9) {
            val opposite = getOpposite(num)
            if(numberStore[num] < 1 && numberStore[opposite!!] > 0) {
                num = opposite
            }
            else if(numberStore[num] < 1 && numberStore[opposite!!] < 1){
                result += 1
                addNumberSet(numberStore)
            }
        }
        else if(numberStore[num] < 1) {
            result += 1
            addNumberSet(numberStore)
        }
        numberStore[num] -= 1
    }
    println(result)
}

fun getOpposite(num: Int): Int? {
    if(num == 6) return 9
    else if(num == 9) return 6
    return null
}

fun addNumberSet(list: MutableList<Int>) {
    for(i in list.indices) list[i] += 1
}