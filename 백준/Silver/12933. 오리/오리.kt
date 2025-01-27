import kotlin.math.*
import kotlin.system.exitProcess

fun main() {
    val str = readln()
    val status = MutableList(str.length) { false }
    val cryingSound = listOf('q', 'u', 'a', 'c', 'k')
    var processed = 0
    var duck = 0
    while(processed < str.length) {
        var i = 0
        var targetCharIndex = 0
        var soundFragmentCounter = 0
        val soundFragmentIndex = mutableListOf<Int>()
        while(i < str.length) {
            if(!status[i] && str[i] == cryingSound[targetCharIndex]) {
                soundFragmentIndex.add(i)
                targetCharIndex = (targetCharIndex+1)%5
            }
            if(soundFragmentIndex.size == 5) {
                soundFragmentIndex.forEach { status[it] = true }
                soundFragmentCounter += 5
                soundFragmentIndex.clear()
            }
            i += 1
        }
        if(soundFragmentCounter == 0) {
            println(-1)
            exitProcess(0)
        }
        processed += soundFragmentCounter
        duck += 1
    }
    println(duck)
}