import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.*

fun main() {
    while(true) {
        val (a, b) = readln().split(" ").map { it.toInt() }
        if(a == 0 && b == 0) break

        if(a > b) println("Yes")
        else println("No")
    }
}