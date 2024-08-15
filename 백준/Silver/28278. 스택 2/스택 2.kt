import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.*

/*
    1 X: 정수 X를 스택에 넣는다. (1 ≤ X ≤ 100,000)
    2: 스택에 정수가 있다면 맨 위의 정수를 빼고 출력한다. 없다면 -1을 대신 출력한다.
    3: 스택에 들어있는 정수의 개수를 출력한다.
    4: 스택이 비어있으면 1, 아니면 0을 출력한다.
    5: 스택에 정수가 있다면 맨 위의 정수를 출력한다. 없다면 -1을 대신 출력한다.
 */
val bw = BufferedWriter(OutputStreamWriter(System.out))

fun main() {
    val s = mutableListOf<Int>()
    repeat(readln().toInt()) {
        val c = readln().split(" ").map { it.toInt() }
        when (c.first()) {
            1 -> { s.add(c.last()) }
            2 -> { if (s.isNotEmpty()) bw.write("${s.removeLast()}\n") else bw.write("-1\n") }
            3 -> bw.write("${s.size}\n")
            4 -> if (s.isEmpty()) bw.write("1\n") else bw.write("0\n")
            else -> if (s.isNotEmpty()) bw.write("${s.last()}\n") else bw.write("-1\n")
        }
    }
    bw.flush()
    bw.close()
}
