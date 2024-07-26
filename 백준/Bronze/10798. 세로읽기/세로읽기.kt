import java.util.*

fun main() {
    val arr = MutableList(5) { MutableList<String>(15) { "" } }
    repeat(5) { i ->
        readln().forEachIndexed { j, c -> arr[i][j] = c.toString() }
    }
    for(i in 0..14) {
        for(j in 0..4) {
            print(arr[j][i])
        }
    }
}