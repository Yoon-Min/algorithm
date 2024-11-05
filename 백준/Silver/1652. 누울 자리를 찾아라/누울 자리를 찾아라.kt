import kotlin.math.*

fun main() {
    val n = readln().toInt()
    val horizontalMap = mutableListOf<String>()
    var horizontal = 0
    var vertical = 0
    repeat(n) {
        val line = readln()
        horizontalMap.add(line)
    }
    val verticalMap = (0..<n).map { i -> (0..<n).map { j -> horizontalMap[j][i] }.joinToString("") }
    horizontalMap.forEach { str ->
        horizontal += getCount(str, n)
    }
    verticalMap.forEach { str ->
        vertical += getCount(str, n)
    }
    println("$horizontal $vertical")
}

fun getCount(str: String, n: Int): Int {
    var i = 0
    var counter = 0
    var result = 0
    while(i < n) {
        if(str[i] == '.') {
            counter++
        }
        else if(counter > 1) {
            result += 1
            counter = 0
        }
        else {
            counter = 0
        }
        i += 1
    }
    if(counter > 1) result += 1
    return result
}

