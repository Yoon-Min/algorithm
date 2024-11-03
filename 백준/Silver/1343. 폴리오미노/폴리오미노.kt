import kotlin.math.*

fun main() {
    val result = mutableListOf<Int>()
    val inp = readln()
    var i = 0
    var xCounter = 0
    while(i < inp.length) {
        if(inp[i] == 'X') {
            xCounter += 1
        }
        else if(xCounter > 0) {
            result.add(xCounter)
            xCounter = 0
            result.add(0)
        }
        else {
            result.add(0)
        }
        i += 1
    }
    if(xCounter > 0) result.add(xCounter)
    if(result.any { it%2 == 1}) println(-1)
    else {
        result.forEach {
            if(it == 0) print('.')
            else {
                repeat(it/4) { print("AAAA") }
                if(it%4 == 2) print("BB")
            }
        }
    }
}


