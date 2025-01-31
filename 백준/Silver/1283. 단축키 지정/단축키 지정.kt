import kotlin.math.*
import kotlin.system.exitProcess

val s = mutableSetOf<Char>()
val result = mutableListOf<MutableList<String>>()

fun main() {
    val n = readln().toInt()
    for(i in 0..<n) {
        val line = readln()
        val words = line.split(" ").toMutableList()
        var isFind = false
        result.add(words)

        for(j in words.indices) {
            val word = words[j]
            if(!isInSet(word.first())) {
                s.add(word.first())
                result[result.lastIndex][j] = word.replaceFirst(word.first().toString(), "[${word.first()}]")
                isFind = true
                break
            }
        }

        if(isFind) continue

        for(j in words.indices) {
            val word = words[j]
            for(k in 1..word.lastIndex) {
                val char = word[k]
                if(!isInSet(char)) {
                    s.add(char)
                    result[result.lastIndex][j] = word.replaceFirst(char.toString(), "[${char}]")
                    isFind = true
                    break
                }
            }
            if(isFind) break
        }
    }
    result.forEach {
        println(it.joinToString(" "))
    }
}

fun isInSet(char: Char): Boolean {
    return s.contains(char.lowercaseChar()) || s.contains(char.uppercaseChar())
}
