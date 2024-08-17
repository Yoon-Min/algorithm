import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.*

/*
    1 X: 정수 X를 덱의 앞에 넣는다. (1 ≤ X ≤ 100,000)
    2 X: 정수 X를 덱의 뒤에 넣는다. (1 ≤ X ≤ 100,000)
    3: 덱에 정수가 있다면 맨 앞의 정수를 빼고 출력한다. 없다면 -1을 대신 출력한다.
    4: 덱에 정수가 있다면 맨 뒤의 정수를 빼고 출력한다. 없다면 -1을 대신 출력한다.
    5: 덱에 들어있는 정수의 개수를 출력한다.
    6: 덱이 비어있으면 1, 아니면 0을 출력한다.
    7: 덱에 정수가 있다면 맨 앞의 정수를 출력한다. 없다면 -1을 대신 출력한다.
    8: 덱에 정수가 있다면 맨 뒤의 정수를 출력한다. 없다면 -1을 대신 출력한다.
 */


val bw = BufferedWriter(OutputStreamWriter(System.out))

fun main() {
    val dq = ArrayDeque<Int>()
    repeat(readln().toInt()) {
        val com = readln().split(" ").map { it.toInt() }
        when(com.first()) {
            1 -> dq.addFirst(com.last())
            2 -> dq.addLast(com.last())
            3 -> if(dq.isNotEmpty()) bufferPrint(dq.removeFirst().toString()) else bufferPrint("-1")
            4 -> if(dq.isNotEmpty()) bufferPrint(dq.removeLast().toString()) else bufferPrint("-1")
            5 -> bufferPrint(dq.size.toString())
            6 -> if(dq.size == 0) bufferPrint("1") else bufferPrint("0")
            7 -> if(dq.size > 0) bufferPrint(dq.first().toString()) else bufferPrint("-1")
            else -> if(dq.size > 0) bufferPrint(dq.last().toString()) else bufferPrint("-1")
        }
    }
    bw.flush()
    bw.close()
}

fun bufferPrint(str: String) {
    bw.write(str + "\n")
}
