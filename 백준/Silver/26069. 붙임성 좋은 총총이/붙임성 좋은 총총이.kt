import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.*

//val bw = BufferedWriter(OutputStreamWriter(System.out))

fun main() {
    val rainbowStatus = hashMapOf<String, Boolean>()
    val host = "ChongChong"
    var counter = 1
    rainbowStatus[host] = true

    repeat(readln().toInt()) {
        val (a, b) = readln().split(" ")
        if(!rainbowStatus.containsKey(a)) rainbowStatus[a] = false
        if(!rainbowStatus.containsKey(b)) rainbowStatus[b] = false

        if(!rainbowStatus[a]!! && rainbowStatus[b]!!) {
            rainbowStatus[a] = true
            counter += 1
        }
        else if(rainbowStatus[a]!! && !rainbowStatus[b]!!) {
            rainbowStatus[b] = true
            counter += 1
        }
    }
    println(counter)
}
