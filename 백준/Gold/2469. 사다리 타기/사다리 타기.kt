import kotlin.system.exitProcess
import kotlin.math.*


fun main() {
    val k = readln().toInt()
    val n = readln().toInt()
    val finalSequence = readln()
    val ladderMap = List(n) { readln().toMutableList() }
    val searchLine = ladderMap.indexOfFirst { it.first() == '?' }
    val initChar = List(k) { (65+it).toChar() }
    val result = mutableListOf<Char>()

    val top = MutableList(k) {'X'}
    val bottom = MutableList(k) {'X'}

    for(i in 0 until k) {
        var cur = i
        for(j in 0 until searchLine) {
            if(cur == k-1) {
                if(ladderMap[j][cur-1] == '-') cur -= 1
            }
            else if(cur == 0) {
                if(ladderMap[j][cur] == '-') cur += 1
            }
            else {
                if(ladderMap[j][cur] == '-') cur += 1
                else if(ladderMap[j][cur-1] == '-') cur -= 1
            }
        }
        top[cur] = initChar[i]
    }

    for(i in 0 until k) {
        var cur = i
        for(j in n-1 downTo searchLine+1) {
            if(cur == k-1) {
                if(ladderMap[j][cur-1] == '-') cur -= 1
            }
            else if(cur == 0) {
                if(ladderMap[j][cur] == '-') cur += 1
            }
            else {
                if(ladderMap[j][cur] == '-') cur += 1
                else if(ladderMap[j][cur-1] == '-') cur -= 1
            }
        }
        bottom[cur] = finalSequence[i]
    }

    for(i in 0 until k-1) {
        if(top[i] == bottom[i]) {
            result.add('*')
        }
        else {
            val tmp = top[i]
            top[i] = top[i+1]
            top[i+1] = tmp
            result.add('-')
        }
    }
    for(i in 0 until k) {
        if(top[i] != bottom[i]) {
            println(List(k-1) {'x'}.joinToString(""))
            return
        }
    }
    println(result.joinToString(""))
}