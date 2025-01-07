import kotlin.math.*

fun main() {
    val n = readln().toInt()
    val top = mutableListOf<Char>()
    val bottom = mutableListOf<Char>()
    for(i in 1..n) {
        if(i%2 == 0) {
            bottom.add(' ')
            bottom.add('*')
        }
        else {
            top.add('*')
            top.add(' ')
        }
    }
    for(i in 1..n) {
        println(top.joinToString(""))
        println(bottom.joinToString(""))
    }
}

